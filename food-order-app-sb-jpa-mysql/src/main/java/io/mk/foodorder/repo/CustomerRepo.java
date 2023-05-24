package io.mk.foodorder.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import io.mk.foodorder.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {

}
