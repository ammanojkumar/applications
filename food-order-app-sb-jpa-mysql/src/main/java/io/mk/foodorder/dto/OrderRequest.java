package io.mk.foodorder.dto;

import java.util.Set;

import io.mk.foodorder.entity.OrderItem;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {

	private Integer custId;
	private Set<OrderItem> orderItems;
}
