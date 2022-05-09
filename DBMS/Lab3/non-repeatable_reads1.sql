use [RestaurantManager]

--- T2
begin transaction
waitfor delay '00:00:10'
update Chefs set Age = 30 Where Id = 14
commit transaction

