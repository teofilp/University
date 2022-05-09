create or alter function validateChef(@chef varchar(50), @age int)
returns bit as
begin
    declare @valid as bit = 1;

    if (len(@chef) = 0)
    begin
        set @valid = 0
    end

    if (@age < 18)
    begin
        set @valid = 0
    end

    return @valid
end

create or alter function validateRestaurant(@restaurant varchar(300), @maxChefs int)
returns bit as
begin
    declare @valid as bit = 1;

    if (len(@restaurant) = 0)
    begin
        set @valid = 0
    end

    if (@maxChefs <= 0)
    begin
        set @valid = 0
    end

    return @valid
end