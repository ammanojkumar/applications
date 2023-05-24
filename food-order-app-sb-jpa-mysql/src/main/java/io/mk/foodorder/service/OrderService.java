package io.mk.foodorder.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.mk.foodorder.dto.OrderAcknowledgement;
import io.mk.foodorder.dto.OrderRequest;
import io.mk.foodorder.dto.PaymentAcknowledgement;
import io.mk.foodorder.dto.PaymentRequest;
import io.mk.foodorder.entity.Customer;
import io.mk.foodorder.entity.Order;
import io.mk.foodorder.entity.OrderItem;
import io.mk.foodorder.entity.PaymentInfo;
import io.mk.foodorder.entity.Supplier;
import io.mk.foodorder.exception.PaymentFailedException;
import io.mk.foodorder.model.OrderStatus;
import io.mk.foodorder.model.PaymentStatus;
import io.mk.foodorder.repo.CustomerRepo;
import io.mk.foodorder.repo.OrderItemRepo;
import io.mk.foodorder.repo.OrderRepo;
import io.mk.foodorder.repo.SupplierRepo;

@Service
public class OrderService {

	@Autowired
	private OrderItemRepo orderItemRepo;

	@Autowired
	private OrderRepo orderRepo;
	
	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private SupplierRepo supplierRepo;

	@Autowired
	private PaymentService paymentService;

	@Transactional
	public OrderAcknowledgement createOrder(OrderRequest orderRequest) {
		Integer custId = orderRequest.getCustId();
		Customer customer = customerRepo.findById(custId).get();
		System.out.println("Creating order for " + customer.getName());
		List<Supplier> suppliers = supplierRepo.findAll();
		Supplier supplier = suppliers.get(new Random().nextInt(suppliers.size()));

		System.out.println("Assigning supplier " + supplier.getName());
		Set<OrderItem> orderItems = orderRequest.getOrderItems();

		System.out.println("Processing order..");
		OrderAcknowledgement orderAck = new OrderAcknowledgement();
		int taxPercent = 5;
		int netAmount = orderItems.stream().map(oi -> oi.getQty() * oi.getPrice()).mapToInt(Integer::valueOf).sum();
		int taxAmount = netAmount * taxPercent / 100;
		int taxableAmount = netAmount + taxAmount;

		Order order = new Order();
		order.setCustomer(customer);
		Set<Supplier> s = new HashSet<>();
		s.add(supplier);
		order.setSuppliers(s);
		order.setDate(new Date());
		order.setNetAmount(netAmount);
		order.setTaxAmount(taxAmount);
		order.setTotalAmount(taxableAmount);
		order.setOrderStatus(OrderStatus.PROCESSING);

		orderItems.forEach(oi -> oi.setOrder(order));
		// Saving Order details
		orderItemRepo.saveAll(orderItems);

		System.out.println("Order processed!");
		orderAck.setBillAmount(taxableAmount);
		orderAck.setCustomerId(customer.getId());
		orderAck.setStatus(OrderStatus.PROCESSING);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return orderAck;
	}

	public PaymentAcknowledgement makePayment(Order order, PaymentRequest paymentRequest) {
		// Making payment
		int billAmount = order.getOrderItems().stream().map(oi -> oi.getPrice() * oi.getQty())
				.mapToInt(Integer::valueOf).sum();
		
		PaymentInfo paymentResp = paymentService.makePayment(paymentRequest.getPaymentInfo(), billAmount);
		if (!paymentResp.getStatus().equals(PaymentStatus.PAYMENT_SUCCESS)) {
			throw new PaymentFailedException("Payment Failed for " + order.getCustomer().getUsername());
		}

//		// Saving Payment details
		PaymentAcknowledgement paymentAck = new PaymentAcknowledgement();
		paymentAck.setOrderId(order.getId());
		paymentAck.setCustomerId(order.getCustomer().getId());
//		paymentAck.setSupplierId(order.getSuppliers().iterator().next().getId());
		paymentAck.setBillAmount(billAmount);
		paymentAck.setPaymentStatus(paymentResp.getStatus());
		paymentAck.setTransactionId(paymentResp.getTxnId());
		return paymentAck;
	}

	public Order getOrder(Integer orderId) {
		Optional<Order> orderOp = orderRepo.findById(orderId);
		return orderOp.get();
	}
}
