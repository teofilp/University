use [RestaurantsManager]


create or alter procedure AddChefsWithRestaurantsRollback(@chef varchar(50), @age int, @restaurant varchar(300), @maxChefs int)
as
begin
    declare @chefId as int = 0
    declare @restaurantId as int = 0
    begin transaction
    begin try
        if (dbo.validateChef(@chef, @age) = 0)
        begin
            raiserror ('Invalid chef', 14, 1);
        end
        insert into Chefs (Name, Age) values (@chef, @age)
        set @chefId = @@identity
        exec Log 'insert', 'Chefs'

        if (dbo.validateRestaurant(@restaurant, @maxChefs) = 0)
        begin
            raiserror ('Invalid restaurant', 14, 1)
        end
        insert into Restaurants(Name, MaxChefs) values (@restaurant, @maxChefs)
        set @restaurantId = @@identity
        exec Log 'insert', 'Restaurants'

        insert into RestaurantsChefs (ChefId, RestaurantId, CreateDate) values (@chefId, @restaurantId, getdate())
        exec Log 'insert', 'RestaurantsChefs'
        commit transaction
        select 'Transaction completed sucessfully!'
    end try
    begin catch
        select @@error
        rollback transaction
        select 'Transaction was aborted!'
    end catch
end


--- happy case
exec AddChefsWithRestaurantsRollback 'Mozart', 18, 'Mozart''s Restaurant', 1
select * from Chefs
select * from Restaurants
select * from RestaurantsChefs

--- error
exec AddChefsWithRestaurantsRollback 'Mozart', 17, 'Mozart''s Restaurant', 1
select * from Chefs
select * from Restaurants
select * from RestaurantsChefs
