package io.mk.foodorder.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Supplier_tbl")
@Getter
@Setter
public class Supplier {

	@Id
	private Integer id;

	private String name;

	@ManyToMany(targetEntity = Order.class)
	@JoinTable(name = "SUPPLIER_AND_ORDER_TBL", joinColumns = @JoinColumn(name = "suplierId"), inverseJoinColumns = @JoinColumn(name = "orderId"))
	private Set<Order> orders;

	public Supplier() {
	}

	public Supplier(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

}
