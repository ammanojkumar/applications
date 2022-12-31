package io.mk.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import io.mk.jpa.entity.Order;

public interface OrderRepo extends JpaRepository<Order, Integer> {

}
