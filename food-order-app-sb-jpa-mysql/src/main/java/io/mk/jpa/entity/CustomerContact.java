package io.mk.jpa.entity;

import javax.persistence.Embeddable;

@Embeddable
public class CustomerContact {

	private String phone;
	private String email;

	public CustomerContact() {
	}

	public CustomerContact(String phone, String email) {
		super();
		this.phone = phone;
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
