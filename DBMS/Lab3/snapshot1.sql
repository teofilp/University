use RestaurantManager

alter database RestaurantManager set allow_snapshot_isolation on
begin transaction

waitfor delay  '00:00:10'

update Chefs set Name = 'Mozzart_new' where Id = 14

waitfor delay '00:00:10'
commit transaction