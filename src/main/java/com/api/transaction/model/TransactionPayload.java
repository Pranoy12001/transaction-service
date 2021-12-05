package com.api.transaction.model;

import java.util.Date;

public class TransactionPayload {
    private String requestId;
    private Date transactionTime;
    private String requester;
    private String transactionType;
    private String sourceAccountNumber;
    private String amount;
    private String destinationAccountNumber;
    private String note;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Date getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(Date transactionTime) {
        this.transactionTime = transactionTime;
    }

    public String getRequester() {
        return requester;
    }

    public void setRequester(String requester) {
        this.requester = requester;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getSourceAccountNumber() {
        return sourceAccountNumber;
    }

    public void setSourceAccountNumber(String sourceAccountNumber) {
        this.sourceAccountNumber = sourceAccountNumber;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDestinationAccountNumber() {
        return destinationAccountNumber;
    }

    public void setDestinationAccountNumber(String destinationAccountNumber) {
        this.destinationAccountNumber = destinationAccountNumber;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "TransactionPayload{" +
                "requestId='" + requestId + '\'' +
                ", transactionTime=" + transactionTime +
                ", requester='" + requester + '\'' +
                ", transactionType='" + transactionType + '\'' +
                ", sourceAccountNumber='" + sourceAccountNumber + '\'' +
                ", amount='" + amount + '\'' +
                ", destinationAccountNumber='" + destinationAccountNumber + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
