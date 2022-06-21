create table IF NOT EXISTS UserTest(
    userId integer auto_increment unique primary key
    ,name varchar2(250) not null
    ,email    varchar2(100) not null unique
    ,password varchar2(25)  not null
    ,created timestamp not null
    ,modified timestamp not null
    ,lastLogin timestamp not null
    ,token varchar2(250)
    ,isActive boolean default false
    ,constraint User_PK primary key (userId)
);

create unique index IF NOT EXISTS User_UserId_UINDEX
    on UserTest(userId);

create table IF NOT EXISTS Phone
(
    phoneId      bigint auto_increment unique primary key
    ,number      int not null
    ,cityCode    int not null
    ,countryCode int not null
    ,userId      bigint not null
    ,constraint Phone_PK primary key (phoneId)
    ,constraint Phone_User_UserId_FK
        foreign key (userId) references UserTest(userId)
);

create unique index IF NOT EXISTS Phone_PhoneId_UINDEX
    on Phone(phoneId);
