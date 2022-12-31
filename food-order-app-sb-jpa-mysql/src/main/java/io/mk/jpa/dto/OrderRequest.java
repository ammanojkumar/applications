package io.mk.jpa.dto;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;

import io.mk.jpa.entity.Customer;
import io.mk.jpa.entity.Order;
import io.mk.jpa.entity.Supplier;

public class OrderRequest {

	@OneToOne(cascade = CascadeType.ALL)
	private Customer customer;

	@OneToOne(cascade = CascadeType.ALL)
	private Supplier supplier;

	private Order order;
}
