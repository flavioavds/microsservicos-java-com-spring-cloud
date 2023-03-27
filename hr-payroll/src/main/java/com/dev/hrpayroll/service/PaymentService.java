package com.dev.hrpayroll.service;

import org.springframework.stereotype.Service;

import com.dev.hrpayroll.entities.Payment;

@Service
public class PaymentService {
	
	public Payment getPayment(long workerId, int days) {
		return new Payment("Bob", 200.0, days);
	}
	
}
