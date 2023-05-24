package io.mk.foodorder.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import io.mk.foodorder.model.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ORDER_TBL")
@Getter
@Setter
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(targetEntity = Customer.class)
	@JoinColumn(name = "custId")
	private Customer customer;

	@ManyToMany(targetEntity = Supplier.class)

	@JoinTable(name = "SUPPLIER_AND_ORDER_TBL", joinColumns = @JoinColumn(name = "orderId"), inverseJoinColumns = @JoinColumn(name = "suplierId"))
	private Set<Supplier> suppliers;

	@OneToMany(targetEntity = OrderItem.class, mappedBy = "order", cascade = CascadeType.ALL,
			fetch = FetchType.EAGER)
	private Set<OrderItem> orderItems;

	@Temporal(TemporalType.DATE)
	private Date date;

	private Integer netAmount;

	private Integer taxAmount;

	private Integer totalAmount;

	private OrderStatus orderStatus;

}
