package io.mk.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import io.mk.jpa.entity.Order;
import io.mk.jpa.entity.Payment;

public interface PaymentRepo extends JpaRepository<Payment, Integer> {

}
