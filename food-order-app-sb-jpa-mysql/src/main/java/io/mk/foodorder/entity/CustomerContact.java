package io.mk.foodorder.entity;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
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

}
