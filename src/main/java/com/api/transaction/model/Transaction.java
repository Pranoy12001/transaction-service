package com.api.transaction.model;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table
public class Transaction {
    @Id
    @Column(name = "request_id", nullable = false)
    private String requestId;
    @NotNull
    private Date transactionTime;
    @NotNull
    private String requester;
    @NotNull
    private String transactionType;
    @NotNull
    private String sourceAccountNumber;
    @NotNull
    private double amount;
    @NotNull
    private String destinationAccountNumber;
    @NotNull
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
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
        return "Transaction{" +
                "requestId='" + requestId + '\'' +
                ", transactionTime='" + transactionTime + '\'' +
                ", requester='" + requester + '\'' +
                ", transactionType='" + transactionType + '\'' +
                ", sourceAccountNumber='" + sourceAccountNumber + '\'' +
                ", amount=" + amount +
                ", destinationAccountNumber='" + destinationAccountNumber + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
