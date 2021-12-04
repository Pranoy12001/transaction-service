package com.api.transaction.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Transaction {
    @Id
    @Column(name = "request_id", nullable = false)
    private String requestId;
    private Date transactionTime;
    private String requester;
    @JsonIgnore
    private String transactionType;
    @Transient
    @JsonProperty(value = "transactionType")
    private String encodedTransactionType;
    @JsonIgnore
    private String sourceAccountNumber;
    @Transient
    @JsonProperty(value = "sourceAccountNumber")
    private String encodedSourceAccountNumber;
    @JsonIgnore
    private double amount;
    @Transient
    @JsonProperty(value = "amount")
    private String encodedAmount;
    @JsonIgnore
    private String destinationAccountNumber;
    @Transient
    @JsonProperty(value = "destinationAccountNumber")
    private String encodedDestinationAccountNumber;
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

    public String getEncodedTransactionType() {
        return encodedTransactionType;
    }

    public void setEncodedTransactionType(String encodedTransactionType) {
        this.encodedTransactionType = encodedTransactionType;
    }

    public String getEncodedSourceAccountNumber() {
        return encodedSourceAccountNumber;
    }

    public void setEncodedSourceAccountNumber(String encodedSourceAccountNumber) {
        this.encodedSourceAccountNumber = encodedSourceAccountNumber;
    }

    public String getEncodedAmount() {
        return encodedAmount;
    }

    public void setEncodedAmount(String encodedAmount) {
        this.encodedAmount = encodedAmount;
    }

    public String getEncodedDestinationAccountNumber() {
        return encodedDestinationAccountNumber;
    }

    public void setEncodedDestinationAccountNumber(String encodedDestinationAccountNumber) {
        this.encodedDestinationAccountNumber = encodedDestinationAccountNumber;
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
