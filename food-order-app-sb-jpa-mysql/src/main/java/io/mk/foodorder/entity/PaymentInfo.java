package io.mk.foodorder.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.mk.foodorder.model.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Payment_tbl")
@Getter
@Setter
public class PaymentInfo {

	@Id
	private Integer id;
	private String txnId;
	private String cardNo;
	private PaymentStatus status;

}
