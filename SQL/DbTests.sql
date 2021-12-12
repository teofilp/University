alter procedure RunTests @description nvarchar (300), @testIds nvarchar(300)
as
    declare @testRunId int;
    declare @testId int;
    insert into test.dbo.[TestRuns] (Description) values (@description)

    select top 1 @testRunId = TestRunId
    from TestRuns
    order by TestRunID desc

    declare test_cursor1 cursor for
        select convert(int, value) testId from string_split(@testIds, ',')

    open test_cursor1
    fetch next from test_cursor1 into @testId

    while @@fetch_status = 0
    begin
        print @testId
        insert into TestPerformance (TestId, TestRunId) values (@testId, @testRunId)
        fetch next from test_cursor1 into @testId
    end

    close test_cursor1
    deallocate test_cursor1

    exec TestRemoveAll @testRunId = @testRunId
    exec TestInsertAll @testRunId = @testRunId
    exec TestViewsAll  @testRunId = @testRunId

    select t.Name, DATEDIFF(millisecond , tp.StartAt, tp.EndAt) as ElapsedTime
    from TestPerformance tp
    inner join Tests t on tp.TestId = t.TestID
    where TestRunId = @testRunId
go

RunTests @description = 'my description', @testIds = '1, 2, 3, 4, 5, 6, 7, 8, 9'

alter procedure TestViewsAll @testRunId int
as
    declare @testId int;

    declare views_cursor cursor
    for
        select ts.TestID
        from [Tests] ts
        inner join [TestViews] ttb on ttb.TestID = ts.TestID
        inner join [TestPerformance] tp on tp.TestId = ts.TestID and tp.TestRunId = @testRunId

    open views_cursor

    fetch next from views_cursor into @testId
    while @@fetch_status = 0
        begin
            print @testId
            exec TestView @testId = @testId, @testRunId = @testRunId
            fetch next from views_cursor into @testId
        end

    close views_cursor
    deallocate views_cursor;
go

alter procedure TestView @testId int, @testRunId int as
    declare @startAt datetime;
    declare @endAt datetime;
    declare @viewName nvarchar(300);
    declare @sqlScript nvarchar(300) = 'select * from ';
    select top 1 @viewName = v.Name
    from Tests ts inner join TestViews tv on ts.TestID = tv.TestID
    inner join Views v on v.ViewID = tv.ViewID
    where ts.TestID = @testId

    set @sqlScript = @sqlScript + @viewName;

    set @startAt = GETDATE();

    exec sp_executesql @sqlScript

    set @endAt = GETDATE();

    update TestPerformance
    set StartAt = @startAt, EndAt = @endAt
    where TestId = @testId and TestRunId = @testRunId
go

alter procedure TestRemoveAll @testRunId int
as
    declare @testId int;

    declare delete_cursor cursor
    for
        select ts.TestID
        from [Tests] ts
        inner join [TestTables] ttb on ttb.TestID = ts.TestID
        inner join [TestPerformance] tp on tp.TestId = ts.TestID and tp.TestRunId = @testRunId
        where ttb.IsDelete = 1
        order by ttb.Position

    open delete_cursor

    fetch next from delete_cursor into @testId
    while @@fetch_status = 0
        begin
            print @testId
            exec TestRemove @testId = @testId, @testRunId = @testRunId
            fetch next from delete_cursor into @testId
        end

    close delete_cursor
    deallocate delete_cursor;
go

create procedure TestInsertAll @testRunId int
as
    declare @testId int;

    declare insert_cursor cursor
    for
        select ts.TestID
        from [Tests] ts
        inner join [TestTables] ttb on ttb.TestID = ts.TestID
        inner join [TestPerformance] tp on tp.TestId = ts.TestID and tp.TestRunId = @testRunId
        where ttb.IsDelete = 0
        order by ttb.Position

    open insert_cursor

    fetch next from insert_cursor into @testId
    while @@fetch_status = 0
        begin
            print @testId
            exec TestInsert @testId = @testId, @testRunId = @testRunId
            fetch next from insert_cursor into @testId
        end

    close insert_cursor
    deallocate insert_cursor;
go

alter procedure TestInsert @testId int, @testRunId int
as
    declare @startAt datetime;
    declare @endAt datetime;
    declare @rows int;
    declare @tableName nvarchar(1000) = [dbo].[getTableNameFromTest](@testId);
    declare @tableColumns table(COLUMN_NAME varchar(300), DATA_TYPE varchar(300));

    --- get number of rows to insert
    select top 1 @rows = tb.NoOfRows
    from [Tests] ts inner join [TestTables] tb on ts.TestID = tb.TestID
    where ts.TestID = @testId;

    --- get table column definitions
    insert into @tableColumns (COLUMN_NAME, DATA_TYPE)
    select COLUMN_NAME, DATA_TYPE from INFORMATION_SCHEMA.COLUMNS
    where TABLE_NAME = @tableName;

    select * from @tableColumns;

    -- start working on table inserts
    set @startAt = GETDATE();

    while @rows > 0
        begin
            declare @colName varchar(300);
            declare @colType varchar(300);
            declare @names varchar(5000) = '';
            declare @values varchar(5000) = '';
            declare @insScript nvarchar(4000) = '';

            -- go over columns to generate data
            declare cols cursor for
            select COLUMN_NAME colName, DATA_TYPE colType from @tableColumns

            open cols
            fetch next from cols into @colName, @colType

            while @@fetch_status = 0
                begin
                    declare @sep varchar(10) = '';

                    if @names != '' set @sep = ', ' -- set separator to ', ' if already have somehting in the list

                    if @colName != 'Id'
                        begin
                            -- set foreign keys
                            if @colName like '%Id' set @values = @values + @sep + convert(varchar(100), @rows);
                            else if @colType = 'int' set @values = @values + @sep + convert(varchar(100), floor(rand()*1000));
                            else if @colType = 'varchar' set @values = @values + @sep + '''' + convert(varchar(100), newid()) + '''';

                            set @names = @names + @sep + @colName
                        end
                    fetch next from cols into @colName, @colType
                end

            close cols
            deallocate cols

            set @insScript = 'insert into ' + @tableName + '(' + @names + ') values(' + @values + ')';
            print @insScript
            exec sp_executesql @insScript

            set @rows = @rows - 1
        end

        set @endAt = GETDATE()

        update TestPerformance
        set StartAt = @startAt, EndAt = @endAt
        where TestRunId = @testRunId and TestId = @testId
go

alter procedure TestRemove @testId int, @testRunId int
as
    declare @startAt datetime;
    declare @endAt datetime;
    declare @sqlScript nvarchar(300)
    declare @tableName varchar(300) = [dbo].[getTableNameFromTest](@testId)

    set @sqlScript = 'delete from ' + @tableName

    set @startAt = GETDATE()

    execute sp_executesql @sqlScript

    set @endAt = GETDATE()

    --- reseed identity
    set @sqlScript = 'IF (OBJECTPROPERTY(OBJECT_ID(''' + @tableName + '''), ''TableHasIdentity'') = 1)  ' +
                     'DBCC CHECKIDENT (' + '[' + @tableName + ']' + ', RESEED, 0);'
    execute sp_executesql @sqlScript

    update TestPerformance
    set StartAt = @startAt, EndAt = @endAt
    where TestId = @testId and TestRunId = @testRunId

    print @startAt
    print @endAt
go

alter function getTableNameFromTest (@testId int)
returns nvarchar(1000)
as begin
    declare @tableName nvarchar(1000)

    select top 1 @tableName =  tb.Name
    from [TestTables] ttb
    inner join [Tables] tb on ttb.TableID = tb.TableID
    where ttb.TestID = @testId

    return @tableName
end
go