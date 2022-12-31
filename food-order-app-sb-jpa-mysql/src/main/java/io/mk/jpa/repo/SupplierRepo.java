package io.mk.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import io.mk.jpa.entity.Supplier;

public interface SupplierRepo extends JpaRepository<Supplier, Integer> {

}
