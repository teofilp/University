use [RestaurantManager]

--- T1
begin transaction
waitfor delay '00:00:10'
insert into Chefs (Name, Age) values ('phantom chef', 20)
commit transaction