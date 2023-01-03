package io.mk.app1insurance.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Insurance {

	@Id
	@GeneratedValue
	private Integer insuranceId;
	private String companyName;
	private String brand;
	private String model;
	private Integer idv;
	private Integer insuranceAmount;

	public Insurance() {
		this.companyName = "Service1";
	}

	public Insurance(String brand, String model, Integer idv, Integer insuranceAmount) {
		super();
		this.brand = brand;
		this.model = model;
		this.idv = idv;
		this.insuranceAmount = insuranceAmount;
		this.companyName = "Service1";
	}

	public Integer getInsuranceId() {
		return insuranceId;
	}

	public void setInsuranceId(Integer insuranceId) {
		this.insuranceId = insuranceId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getIdv() {
		return idv;
	}

	public void setIdv(int idv) {
		this.idv = idv;
	}

	public int getInsuranceAmount() {
		return insuranceAmount;
	}

	public void setInsuranceAmount(int insuranceAmount) {
		this.insuranceAmount = insuranceAmount;
	}

}
