package io.mk.foodorder.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import io.mk.foodorder.entity.item.ItemDiscountGroup;

public interface DiscGroupRepo extends JpaRepository<ItemDiscountGroup, Integer> {

}
