CREATE TABLE IF NOT EXISTS Account
(
    accountType NVARCHAR(20)  NOT NULL,
    id NVARCHAR(40)  NOT NULL,
    clientID NVARCHAR(40)  NOT NULL,
    balance DOUBLE,
    isWithdrawalAllowed BOOLEAN
);

CREATE TABLE IF NOT EXISTS Transaction
(   transactionID LONG NOT NULL,
    operation NVARCHAR(40) NOT NULL,
    accountType NVARCHAR(20)  NOT NULL,
    id NVARCHAR(40)  NOT NULL,
    clientID NVARCHAR(40)  NOT NULL,
    amount DOUBLE
);

create table RoleEntity
(
    id   INTEGER      not null
        constraint RoleEntity_pk
            primary key,
    name varchar(20) not null
);


create table UserEntity
(
    id  INTEGER not null
        constraint UserEntity_pk
            primary key,
    login    varchar(50),
    password varchar(500),
    role_id  integer
        constraint UserEntity_RoleEntity_id_fk
            references RoleEntity
);

create unique index UserEntity_login_uindex
    on UserEntity (login);