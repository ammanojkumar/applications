package io.mk.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.mk.jpa.entity.Order;
import io.mk.jpa.repo.OrderRepo;

@Service
public class PaymentService {

	@Autowired
	private OrderRepo orderRepo;

	public Order orderFood(Order order) {
		return orderRepo.save(order);
	}
}
