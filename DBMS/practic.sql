create table Agencies (
	Id int primary key identity,
	[Name] nvarchar(300) not null,
	[Location] nvarchar(300) not null,
	Size int not null default 0,
)

create table Employees (
	Id int primary key identity,
	[Name] nvarchar(300) not null,
	[Surname] nvarchar(300) not null,
	Age int not null default 0,
)

create table Employments (
	EmployeeId int not null,
	AgencyId int not null,
	primary key (EmployeeId, AgencyId),
	foreign key (EmployeeId) references Employees(Id),
	foreign key (AgencyId) references Agencies(Id)
)

create table Buildings (
	Id int primary key identity,
	[Location] nvarchar(300) not null,
	[AgencyId] int not null,
	Size int not null default 0,
	foreign key (AgencyId) references Agencies(Id) 
	on delete cascade
)

create table ConstructionMaterials (
	Id int primary key identity,
	[Name] nvarchar(300) not null
)

create table MaterialUsage (
	BuildingId int not null,
	ConstructionMaterialId int not null,
	primary key (BuildingId, ConstructionMaterialId),
	foreign key (BuildingId) references Buildings(Id),
	foreign key (ConstructionMaterialId) references ConstructionMaterials(Id)
)

select * from Agencies
select * from Buildings