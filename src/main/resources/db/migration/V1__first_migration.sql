CREATE TABLE IF NOT EXISTS Account
(
    accountType NVARCHAR(20)  NOT NULL,
    id NVARCHAR(40)  NOT NULL,
    clientID NVARCHAR(40)  NOT NULL,
    balance DOUBLE,
    isWithdrawalAllowed BOOLEAN,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS Transaction
(   transactionID LONG NOT NULL,
    operation NVARCHAR(40) NOT NULL,
    accountType NVARCHAR(20)  NOT NULL,
    id NVARCHAR(40)  NOT NULL,
    clientID NVARCHAR(40)  NOT NULL,
    amount DOUBLE,
    PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES Account(id)
);

create table RoleEntity
(
    id INTEGER NOT NULL,
    name NVARCHAR(20) NOT NULL,
    PRIMARY KEY (id)
);

create table UserEntity
(
    id INTEGER NOT NULL,
    login    NVARCHAR(50) NOT NULL,
    password NVARCHAR(500) NOT NULL,
    role_id  INTEGER NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (role_id) REFERENCES RoleEntity(id)
);


