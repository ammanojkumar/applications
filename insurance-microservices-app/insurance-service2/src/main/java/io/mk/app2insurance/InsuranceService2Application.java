package io.mk.app2insurance;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import io.mk.app2insurance.entity.Insurance;
import io.mk.app2insurance.repository.App2Repo;

@SpringBootApplication
@EnableEurekaClient
public class InsuranceService2Application {

	public static void main(String[] args) {
		SpringApplication.run(InsuranceService2Application.class, args);
	}

	@Autowired
	private App2Repo app1Repo;

	@PostConstruct
	public void addEntries() {
		app1Repo.save(new Insurance("BMW", "A1", 500000, 32000));
		app1Repo.save(new Insurance("BMW", "A2", 600000, 36000));
		app1Repo.save(new Insurance("BMW", "A3", 650000, 40000));
	}
}
