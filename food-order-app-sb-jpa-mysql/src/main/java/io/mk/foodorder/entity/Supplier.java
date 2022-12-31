package io.mk.foodorder.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Supplier_tbl")
public class Supplier {

	@Id
	private Integer suppId;
	private String name;

	public Supplier() {
	}

	public Supplier(Integer suppId, String name) {
		super();
		this.suppId = suppId;
		this.name = name;
	}

	public Integer getSuppId() {
		return suppId;
	}

	public void setSuppId(Integer suppId) {
		this.suppId = suppId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
