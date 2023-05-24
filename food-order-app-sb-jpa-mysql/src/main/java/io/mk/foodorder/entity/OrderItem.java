package io.mk.foodorder.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import io.mk.foodorder.entity.item.Item;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ORDER_ITEM_TBL")
@Getter
@Setter
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToOne(targetEntity = Item.class)
	@JoinColumn(name = "itemId")
	private Item item;

	private Integer qty;
	private Integer price;

	@ManyToOne(targetEntity = Order.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "orderId")
	private Order order;

}
