package io.mk.foodorder.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import io.mk.foodorder.entity.item.Item;

public interface ItemRepo extends JpaRepository<Item, Integer> {

}
