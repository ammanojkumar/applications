package io.mk.foodorder.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import io.mk.foodorder.entity.PaymentInfo;
import io.mk.foodorder.model.PaymentStatus;

@Service
public class PaymentService {

	public PaymentInfo makePayment(PaymentInfo paymentInfo, Integer billAmount) {
		// Do payment by calling banking service
		System.out.println("Bill amount paid for card number: " + paymentInfo.getCardNo() + ". Rs" + billAmount);
		paymentInfo.setTxnId(UUID.randomUUID().toString());
		paymentInfo.setStatus(PaymentStatus.PAYMENT_SUCCESS);
		paymentInfo.setAmont(billAmount);
		return paymentInfo;
	}
}
