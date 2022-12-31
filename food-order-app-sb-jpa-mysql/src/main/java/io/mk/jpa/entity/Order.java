package io.mk.jpa.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Order_tbl")
public class Order {

	@Id
	@GeneratedValue
	private Integer orderId;

	@Temporal(TemporalType.DATE)
	private Date date;

	private Integer netAmount;

	private Integer taxAmount;

	private Integer totalAmount;

	private Integer cust_id;

	private Integer supp_id;

	@OneToMany(cascade = CascadeType.ALL)
	private Set<OrderItem> orderItem;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(Integer netAmount) {
		this.netAmount = netAmount;
	}

	public Integer getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(Integer taxAmount) {
		this.taxAmount = taxAmount;
	}

	public Integer getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Integer getCust_id() {
		return cust_id;
	}

	public void setCust_id(Integer cust_id) {
		this.cust_id = cust_id;
	}

	public Integer getSupp_id() {
		return supp_id;
	}

	public void setSupp_id(Integer supp_id) {
		this.supp_id = supp_id;
	}

	public Set<OrderItem> getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(Set<OrderItem> orderItem) {
		this.orderItem = orderItem;
	}

}
