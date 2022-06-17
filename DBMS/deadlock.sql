use [DBMS]

set deadlock_priority 3
begin transaction
update Buildings 
set [Location] = 'Location updated by transaction 2' 
where Id = 4
waitfor delay '00:00:05'
update Agencies 
set [Name] = 'Name updated by transaction 2' 
where Id = 1
commit transaction