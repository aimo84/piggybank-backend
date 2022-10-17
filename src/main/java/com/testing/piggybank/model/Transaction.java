package com.testing.piggybank.model;

import java.math.BigDecimal;
import java.time.Instant;

public class Transaction {
	private long id;
	private long fromAccountId;
	private long toAccountId;
	private String description;
	private BigDecimal amount;
	private Currency currency;
    private Instant dateTime;
    private Status status;
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getFromAccountId() {
		return fromAccountId;
	}
	public void setFromAccountId(long fromAccountId) {
		this.fromAccountId = fromAccountId;
	}
	public long getToAccountId() {
		return toAccountId;
	}
	public void setToAccountId(long toAccountId) {
		this.toAccountId = toAccountId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Currency getCurrency() {
		return currency;
	}
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	public Instant getDateTime() {
		return dateTime;
	}
	public void setDateTime(Instant dateTime) {
		this.dateTime = dateTime;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
    
}
