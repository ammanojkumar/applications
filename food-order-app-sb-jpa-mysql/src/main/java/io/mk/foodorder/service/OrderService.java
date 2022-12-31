package io.mk.foodorder.service;

import java.util.Date;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.mk.foodorder.dto.OrderAcknowledgement;
import io.mk.foodorder.dto.OrderRequest;
import io.mk.foodorder.entity.Customer;
import io.mk.foodorder.entity.Order;
import io.mk.foodorder.entity.OrderItem;
import io.mk.foodorder.entity.PaymentInfo;
import io.mk.foodorder.entity.Supplier;
import io.mk.foodorder.exception.PaymentFailedException;
import io.mk.foodorder.model.OrderStatus;
import io.mk.foodorder.model.PaymentStatus;
import io.mk.foodorder.repo.OrderRepo;
import io.mk.foodorder.repo.PaymentRepo;

@Service
public class OrderService {

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private OrderRepo orderRepo;

	@Autowired
	private PaymentRepo paymentRepo;

	@Transactional
	public OrderAcknowledgement createOrder(OrderRequest orderRequest) {
		Customer customer = orderRequest.getCustomer();
		Supplier supplier = orderRequest.getSupplier();
		Set<OrderItem> orderItems = orderRequest.getOrderItems();
		PaymentInfo paymentInfo = orderRequest.getPaymentInfo();
		System.out.println("Creating Order for " + orderRequest.getCustomer().getUsername());

		OrderAcknowledgement orderAck = new OrderAcknowledgement();
		int taxPercent = 5;
		Order order = new Order();
		order.setDate(new Date());
		order.setOrderItem(orderItems);
		orderItems.forEach(orderItem -> orderItem.setOrder(order));
		int netAmount = orderItems.stream().map(oi -> oi.getTotalPrice()).mapToInt(Integer::valueOf).sum();
		int taxAmount = netAmount * taxPercent / 100;
		int taxableAmount = netAmount + taxAmount;
		order.setNetAmount(netAmount);
		order.setTaxAmount(taxAmount);
		order.setTotalAmount(taxableAmount);
		order.setCustomerId(customer.getCustId());
		order.setSupplierId(supplier.getSuppId());
		paymentInfo.setAmount(taxableAmount);

		// Saving Order details
		Order orderRes = orderRepo.save(order);

		// Making payment
		PaymentInfo paymentResp = paymentService.makePayment(paymentInfo);
		if (!paymentResp.getStatus().equals(PaymentStatus.PAYMENT_SUCCESS)) {
			order.setOrderStatus(OrderStatus.NOT_ORDERED);
			orderAck.setStatus(OrderStatus.NOT_ORDERED);
			throw new PaymentFailedException("Payment Failed for " + customer.getUsername());
		}

		// Saving Payment details
		paymentRepo.save(paymentResp);
		order.setOrderStatus(OrderStatus.ORDERED);
		orderAck.setStatus(OrderStatus.ORDERED);
		orderAck.setOrderId(orderRes.getOrderId());
		orderAck.setCustomerId(orderRes.getCustomerId());
		orderAck.setSupplierId(orderRes.getSupplierId());
		orderAck.setBillAmount(orderRes.getTotalAmount());

		orderAck.setTransactionId(paymentResp.getTransactionId());
		return orderAck;
	}
}
