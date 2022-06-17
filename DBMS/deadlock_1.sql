use [DBMS]

set deadlock_priority 2
begin transaction
update Agencies 
set [Name] = 'Name updated by transaction 1' 
where Id = 1
waitfor delay '00:00:05'
update Buildings 
set [Location] = 'Location updated by transaction 1' 
where Id = 4
commit transaction