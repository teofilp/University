use [RestaurantManager]

--- T2
set transaction isolation level read uncommitted
begin transaction
select * from Chefs
waitfor delay '00:00:10'
select * from Chefs
commit transaction
--- solution: change transaction isolation level to read committed :)