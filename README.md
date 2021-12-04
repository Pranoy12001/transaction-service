# transaction-service

Database information: </br>
CREATE TABLE account (
id bigint NOT NULL AUTO_INCREMENT,
account_number varchar(255) NOT NULL UNIQUE,
address varchar(255) DEFAULT NULL,
balance double NOT NULL,
name varchar(255) DEFAULT NULL,
status int NOT NULL,
PRIMARY KEY (id)
);
</br></br>
create table transaction(
request_id varchar(255) not null,
amount double precision not null,
destination_account_number varchar(255),
note varchar(255), requester varchar(255),
source_account_number varchar(255),
transaction_time varchar(255),
transaction_type varchar(255), primary key (request_id)
)
</br></br>
INSERT INTO account (account_number, address, balance, name, status)
VALUES ('001241009211439', 'Texas', 5000, 'Sheldon', 1);
</br>
INSERT INTO account (account_number, address, balance, name, status)
VALUES ('32341200923487', 'Nebraska', 5000, 'Penny', 1);
</br>
INSERT INTO account (account_number, address, balance, name, status)
VALUES ('123456789123456', 'California', 5000, 'Leonerd', 1);
</br></br>
EndPoints:</br>
Test Application : localhost:8081/test-application</br>
    curl: </br>
    curl --location --request GET 'localhost:8081/test-application'
</br></br>
Perform Transaction: localhost:8081/transfer-money </br>
Curl: </br>
curl --location --request POST 'localhost:8081/transfer-money' \
--header 'Content-Type: application/json' \
--data-raw '{
"requestId": "XYZ App",
"requester": "XYZ App",
"transactionType": "VFJBTlNGRVI=",
"sourceAccountNumber": "MDAxMjQxMDA5MjExNDM5",
"amount": "MTAw",
"destinationAccountNumber": "MzIzNDEyMDA5MjM0ODc=",
"note": "Transferring amount"
}'
    