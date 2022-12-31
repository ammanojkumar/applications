package io.mk.foodorder.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import io.mk.foodorder.entity.Customer;
import io.mk.foodorder.entity.Order;
import io.mk.foodorder.entity.PaymentInfo;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {

}
