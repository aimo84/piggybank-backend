package com.testing.piggybank.transaction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testing.piggybank.model.Transaction;

@Service
public class TransactionService {
	
	private final TransactionRepository transactionRepository;
	
	@Autowired
	public TransactionService(TransactionRepository transactionRepository) {
		this.transactionRepository = transactionRepository;
	}
	
	public List<Transaction> getTransactions() {
		return transactionRepository.getTransactions();
	}
}
