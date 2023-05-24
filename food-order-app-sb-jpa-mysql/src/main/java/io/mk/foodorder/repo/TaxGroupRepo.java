package io.mk.foodorder.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import io.mk.foodorder.entity.item.ItemTaxGroup;

public interface TaxGroupRepo extends JpaRepository<ItemTaxGroup, Integer> {

}
