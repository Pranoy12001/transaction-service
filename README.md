# transaction-service

<h2>EndPoints:</h2>
<b>Test Application</b> : localhost:8081/test-application</br>
    Curl: </br>
    curl --location --request GET 'localhost:8081/test-application'
</br></br>
<b>Perform Transaction</b>: localhost:8081/transfer-money </br>
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
    