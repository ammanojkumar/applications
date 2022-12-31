package io.mk.foodorder.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import io.mk.foodorder.entity.Supplier;

public interface SupplierRepo extends JpaRepository<Supplier, Integer> {

}
