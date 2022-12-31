package io.mk.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import io.mk.jpa.entity.Item;

public interface ItemRepo extends JpaRepository<Item, Integer> {

}
