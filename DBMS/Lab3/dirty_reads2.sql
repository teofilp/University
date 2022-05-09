use [RestaurantsManager]

--- T2
set transaction isolation level read uncommitted
begin transaction
select * from Chefs
waitfor delay '00:00:15'
select * from Chefs
commit transaction