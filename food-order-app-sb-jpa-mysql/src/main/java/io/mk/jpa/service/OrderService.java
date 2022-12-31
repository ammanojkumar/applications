package io.mk.jpa.service;

import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.mk.jpa.entity.Customer;
import io.mk.jpa.entity.Order;
import io.mk.jpa.entity.OrderItem;
import io.mk.jpa.entity.Supplier;
import io.mk.jpa.repo.OrderRepo;

@Service
public class OrderService {

	@Autowired
	private OrderRepo orderRepo;

//	@Autowired
//	private Random random;

	public Order createOrder(Customer customer, Supplier supplier, Set<OrderItem> orderItems) {
		System.out.println("Creating Order...");
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
		order.setCust_id(customer.getCustId());
		order.setSupp_id(supplier.getSuppId());
		return orderRepo.save(order);
	}
}
