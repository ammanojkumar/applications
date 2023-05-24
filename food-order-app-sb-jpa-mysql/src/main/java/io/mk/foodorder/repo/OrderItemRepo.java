package io.mk.foodorder.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import io.mk.foodorder.entity.OrderItem;

public interface OrderItemRepo extends JpaRepository<OrderItem, Integer> {

}
