package io.mk.foodorder.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import io.mk.foodorder.entity.Order;
import io.mk.foodorder.entity.PaymentInfo;

public interface PaymentRepo extends JpaRepository<PaymentInfo, Integer> {

}
