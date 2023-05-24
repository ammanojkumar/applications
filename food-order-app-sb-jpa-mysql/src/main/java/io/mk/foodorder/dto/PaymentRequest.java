package io.mk.foodorder.dto;

import io.mk.foodorder.entity.PaymentInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequest {

	private Integer custId;
	private PaymentInfo paymentInfo;
	private Integer orderId;
}
