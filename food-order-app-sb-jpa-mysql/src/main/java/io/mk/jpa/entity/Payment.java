package io.mk.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Payment_tbl")
public class Payment {

	@Id
	@GeneratedValue
	private Integer payId;
	private Integer txnId;
	private Integer amount;

	public Integer getPayId() {
		return payId;
	}

	public void setPayId(Integer payId) {
		this.payId = payId;
	}

	public Integer getTxnId() {
		return txnId;
	}

	public void setTxnId(Integer txnId) {
		this.txnId = txnId;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

}
