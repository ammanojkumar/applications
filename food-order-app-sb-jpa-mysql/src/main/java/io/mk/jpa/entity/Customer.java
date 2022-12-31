package io.mk.jpa.entity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Customer_tbl")
public class Customer {

	@Id
	private Integer custId;
	private String username;
	private String password;
	private String name;
	private String address;
	@Embedded
	private CustomerContact contact;

	public Customer() {
	}

	public Customer(Integer custId, String username, String password, String name, String address) {
		super();
		this.custId = custId;
		this.username = username;
		this.password = password;
		this.name = name;
		this.address = address;
	}

	public Integer getCustId() {
		return custId;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public CustomerContact getContact() {
		return contact;
	}

	public void setContact(CustomerContact contact) {
		this.contact = contact;
	}

}
