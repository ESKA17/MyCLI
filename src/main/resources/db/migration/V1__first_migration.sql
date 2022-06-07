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