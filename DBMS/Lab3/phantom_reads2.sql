use [RestaurantManager]

--- T2
set transaction isolation level repeatable read
begin transaction
select count(*) from Chefs
waitfor delay '00:00:15'
select count(*) from Chefs
commit transaction
-- solution: transaction isolation level serializable