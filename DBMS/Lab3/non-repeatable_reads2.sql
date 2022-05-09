use [RestaurantManager]

--- T1
set transaction isolation level read committed
begin transaction
select * from Chefs
waitfor delay '00:00:15'
select * from Chefs
commit transaction
--- solution transaction isolation level repeatable reads