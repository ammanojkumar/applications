package io.mk.foodorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import io.mk.foodorder.service.OrderInitService;

@SpringBootApplication
@EnableTransactionManagement
public class OnlineFoodOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineFoodOrderApplication.class, args);
	}

	@Autowired
	private OrderInitService orderInitService;
	
	private int sleepTime = 0;

	@Bean
	public CommandLineRunner addSupplier() {
		return (args) -> {
			System.out.println("---------------Adding Items---------------");
			orderInitService.addItems();
			Thread.sleep(sleepTime);
			
			System.out.println("---------------Adding Items---------------");
			orderInitService.addCustAndSupp();
			Thread.sleep(sleepTime);
			
			System.out.println("---------------Crating Orders---------------");
			orderInitService.createOrders();
			Thread.sleep(sleepTime);
			
			System.out.println("---------------Making Payment---------------");
			orderInitService.makePayment();
		};
	}
}
