create or alter procedure AddChefsWithRestaurantsRecover(@chef varchar(50), @age int, @restaurant varchar(300), @maxChefs int)
as
begin
    declare @chefId as int = 0
    declare @restaurantId as int = 0
    begin try
        begin transaction
        if (dbo.validateChef(@chef, @age) = 0)
        begin
            raiserror ('Invalid chef', 14, 1);
        end
        insert into Chefs (Name, Age) values (@chef, @age)
        set @chefId = @@identity
        exec Logi 'insert', 'Chefs'
        commit transaction
        select 'Transaction completed sucessfully'
    end try
    begin catch
        rollback transaction
        select 'Transaction aborted!'
    end catch
    begin try
        begin transaction
        if (dbo.validateRestaurant(@restaurant, @maxChefs) = 0)
        begin
            raiserror ('Invalid restaurant', 14, 1)
        end
        insert into Restaurants(Name, MaxChefs) values (@restaurant, @maxChefs)
        set @restaurantId = @@identity
        exec Logi 'insert', 'Restaurants'
        commit transaction
        select 'Transaction completed sucessfully'
    end try
    begin catch
        rollback transaction
        select 'Transaction aborted!'
    end catch
    begin try
        begin transaction
        insert into RestaurantsChefs (ChefId, RestaurantId, CreateDate) values (@chefId, @restaurantId, getdate())
        exec Logi 'insert', 'RestaurantsChefs'
        commit transaction
        select 'Transaction completed sucessfully'
    end try
    begin catch
        rollback transaction
        select 'Transaction aborted!'
    end catch
end

--- happy case
exec AddChefsWithRestaurantsRecover 'Mozart', 20, 'Mozart''s Restaurant', 1
select * from Chefs
select * from Restaurants
select * from RestaurantsChefs

--- first throws error
exec AddChefsWithRestaurantsRecover 'Mozart', 17, 'Mozart''s Restaurant', 1
select * from Chefs
select * from Restaurants
select * from RestaurantsChefs

--- seceond throws error

exec AddChefsWithRestaurantsRecover 'Mozart', 20, 'Mozart''s Restaurant', 0
select * from Chefs
select * from Restaurants
select * from RestaurantsChefs