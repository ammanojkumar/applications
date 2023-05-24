package io.mk.foodorder.entity;

import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Customer_tbl")
@Getter
@Setter
public class Customer {

	@Id
	private Integer id;
	private String username;
	private String password;
	private String name;
	private String address;

	@Embedded
	private CustomerContact contact;

	@OneToMany(targetEntity = Order.class, mappedBy = "customer", fetch = FetchType.EAGER)
	private Set<Order> orders;

	public Customer() {
	}

	public Customer(Integer id, String username, String password, String name, String address) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.address = address;
	}

}
