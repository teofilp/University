use [RestaurantsManager]

--- T1
set transaction isolation level read uncommitted
begin transaction
update Chefs set Age = 25 Where Id = 53
waitfor delay '00:00:10'
rollback transaction