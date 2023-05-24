package io.mk.foodorder.dto;

import io.mk.foodorder.model.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentAcknowledgement {

	private Integer orderId;
	private Integer customerId;
	private Integer supplierId;
	private Integer billAmount;
	private String transactionId;
	private PaymentStatus paymentStatus;
}
