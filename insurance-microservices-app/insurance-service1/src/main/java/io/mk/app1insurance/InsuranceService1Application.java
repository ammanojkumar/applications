package io.mk.app1insurance;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import io.mk.app1insurance.entity.Insurance;
import io.mk.app1insurance.repository.App1Repo;

@SpringBootApplication
@EnableEurekaClient
public class InsuranceService1Application {

	public static void main(String[] args) {
		SpringApplication.run(InsuranceService1Application.class, args);
	}

	@Autowired
	private App1Repo app1Repo;

	@PostConstruct
	public void addEntries() {
		app1Repo.save(new Insurance("BMW", "A1", 500000, 32000));
		app1Repo.save(new Insurance("BMW", "A2", 600000, 36000));
		app1Repo.save(new Insurance("BMW", "A3", 650000, 40000));
	}
}
