package io.mk.foodorder.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import io.mk.foodorder.entity.PaymentInfo;
import io.mk.foodorder.model.PaymentStatus;

@Service
public class PaymentService {

	public PaymentInfo makePayment(PaymentInfo paymentInfo) {
		// Do payment by calling banking service

		if (paymentInfo.getUsername().equals("user3")) {
			paymentInfo.setStatus(PaymentStatus.PAYMENT_FAILED);
		} else {
			paymentInfo.setStatus(PaymentStatus.PAYMENT_SUCCESS);
			paymentInfo.setTransactionId(UUID.randomUUID().toString());
		}
		return paymentInfo;
	}
}
