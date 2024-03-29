package io.mk.foodorder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.mk.foodorder.dto.OrderAcknowledgement;
import io.mk.foodorder.dto.OrderRequest;
import io.mk.foodorder.dto.PaymentAcknowledgement;
import io.mk.foodorder.dto.PaymentRequest;
import io.mk.foodorder.entity.Order;
import io.mk.foodorder.service.OrderService;

@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping("order")
	public OrderAcknowledgement createOrder(@RequestBody OrderRequest orderRequest) {
		return orderService.createOrder(orderRequest);
	}

	@PostMapping("payment")
	public PaymentAcknowledgement makePayment(@RequestBody PaymentRequest paymentRequest) {
		Order order = orderService.getOrder(paymentRequest.getOrderId());
		return orderService.makePayment(order, paymentRequest);
	}
}
