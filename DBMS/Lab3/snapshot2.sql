use RestaurantManager

set transaction isolation level snapshot
begin transaction

select * from Chefs where Id = 14

waitfor delay '00:00:10'

select * from Chefs where Id = 15

update Chefs set Name = 'mama mia' where Id = 14

commit transaction