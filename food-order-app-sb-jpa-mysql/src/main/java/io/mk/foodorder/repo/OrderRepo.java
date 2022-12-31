package io.mk.foodorder.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import io.mk.foodorder.entity.Order;

public interface OrderRepo extends JpaRepository<Order, Integer> {

}
