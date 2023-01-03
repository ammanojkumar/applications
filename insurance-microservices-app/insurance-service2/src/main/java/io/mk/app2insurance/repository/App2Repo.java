package io.mk.app2insurance.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.mk.app2insurance.entity.Insurance;

public interface App2Repo extends JpaRepository<Insurance, Integer> {

	Optional<Insurance> findByBrand(String brand);

	Optional<Insurance> findByBrandAndModel(String brand, String model);

}