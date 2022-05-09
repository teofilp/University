create table Chefs
(
    Id   int identity
        constraint Chefs_pk
            primary key nonclustered,
    Name nvarchar(50) not null,
    Age  int          not null
        constraint CK_TABLE_CHEFS_AGE
            check ([Age] > 18)
)
go

create index Chefs_Id_index
    on Chefs (Id)
go

create table Restaurants
(
    Id       int identity
        primary key,
    Name     nvarchar(300) not null,
    MaxChefs int           not null
)
go

create table RestaurantsChefs
(
    ChefId       int      not null
        references Chefs,
    RestaurantId int      not null
        references Restaurants,
    CreateDate   datetime not null
)
go

create table Logs (
    Id int not null primary key ,
    OperationType varchar(50) not null,
    TableName varchar(50) not null,
    Timestamp datetime not null,
)
