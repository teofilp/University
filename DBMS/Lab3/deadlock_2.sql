use RestaurantManager

set transaction isolation level serializable
begin transaction
update Restaurants set MaxChefs = 10 Where Id = 5
waitfor delay '00:00:05'
update Chefs set Age = 15 Where Id = 14
commit transaction