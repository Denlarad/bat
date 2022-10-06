use stud_Novikov; 
go 
create type custInt from int; 
create type custText from text; 
go

use stud_Novikov;
create table tempTable
(id int identity(1,1) not null,
 Tname char(20) not null,
 descript custText null,
 Ttime time not null,
 quantity custInt not null);

create index tempIndex
on tempTable (Tname,Ttime,quantity)
go
