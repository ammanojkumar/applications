package io.mk.foodorder.entity.item;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ITEM_TBL")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Integer price;

	@ManyToOne
	@JoinColumn(name = "taxGroupId", nullable = false)
	private ItemTaxGroup itemTaxGroup;

	@OneToOne
	@JoinColumn(name = "discountGroupId")
	private ItemDiscountGroup itemDiscountGroup;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ItemCategory itemCategory;

}
