package io.mk.foodorder.dto;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;

import io.mk.foodorder.entity.Customer;
import io.mk.foodorder.entity.Order;
import io.mk.foodorder.entity.OrderItem;
import io.mk.foodorder.entity.PaymentInfo;
import io.mk.foodorder.entity.Supplier;

public class OrderRequest {

	private Customer customer;
	private Supplier supplier;
	private Set<OrderItem> orderItems;
	private PaymentInfo paymentInfo;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public PaymentInfo getPaymentInfo() {
		return paymentInfo;
	}

	public void setPaymentInfo(PaymentInfo paymentInfo) {
		this.paymentInfo = paymentInfo;
	}

}
