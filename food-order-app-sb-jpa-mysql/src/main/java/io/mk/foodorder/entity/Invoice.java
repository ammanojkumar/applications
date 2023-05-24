//package io.mk.foodorder.entity;
//
//import java.util.Set;
//
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToMany;
//import javax.persistence.OneToOne;
//import javax.persistence.Table;
//
//@Entity
//@Table(name = "Invoice_tbl")
//public class Invoice {
//
//	@Id
//	private Integer id;
//
//	@OneToOne(targetEntity = Supplier.class)
//	@JoinColumn(name = "suppId")
//	private Supplier supplier;
//
//	@OneToOne(targetEntity = Customer.class)
//	@JoinColumn(name = "custId")
//	private Customer customer;
//
//	@OneToMany(targetEntity = Order.class, mappedBy = "invoice")
//	private Set<Order> orders;
//
//	@OneToOne(targetEntity = PaymentInfo.class)
//	@JoinColumn(name = "paymentId")
//	private PaymentInfo paymentInfo;
//
//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}
//
//	public Supplier getSupplier() {
//		return supplier;
//	}
//
//	public void setSupplier(Supplier supplier) {
//		this.supplier = supplier;
//	}
//
//	public Customer getCustomer() {
//		return customer;
//	}
//
//	public void setCustomer(Customer customer) {
//		this.customer = customer;
//	}
//
//	public Set<Order> getOrders() {
//		return orders;
//	}
//
//	public void setOrders(Set<Order> orders) {
//		this.orders = orders;
//	}
//
//	public PaymentInfo getPaymentInfo() {
//		return paymentInfo;
//	}
//
//	public void setPaymentInfo(PaymentInfo paymentInfo) {
//		this.paymentInfo = paymentInfo;
//	}
//
//}
