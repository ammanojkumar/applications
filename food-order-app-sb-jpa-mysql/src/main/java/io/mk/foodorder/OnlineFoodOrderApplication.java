package io.mk.foodorder;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import io.mk.foodorder.dto.OrderRequest;
import io.mk.foodorder.entity.Customer;
import io.mk.foodorder.entity.CustomerContact;
import io.mk.foodorder.entity.Item;
import io.mk.foodorder.entity.OrderItem;
import io.mk.foodorder.entity.PaymentInfo;
import io.mk.foodorder.entity.Supplier;
import io.mk.foodorder.repo.CustomerRepo;
import io.mk.foodorder.repo.ItemRepo;
import io.mk.foodorder.repo.SupplierRepo;
import io.mk.foodorder.service.OrderService;

@SpringBootApplication
@EnableTransactionManagement
public class OnlineFoodOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineFoodOrderApplication.class, args);
	}

	@Autowired
	private OrderService orderService;

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private SupplierRepo supplierRepo;

	@Autowired
	private ItemRepo itemRepo;

	final int cnt = 5;

	@Bean
	public Random getRandom() {
		return new Random(cnt);
	}

	@Bean
	public CommandLineRunner addSupplier() {
//		new CommandLineRunner() {
//			@Override
//			public void run(String... args) throws Exception {
//			}
//		};

		return (args) -> {
			if (supplierRepo.findAll().isEmpty()) {
				System.out.println("Adding suppliers...");
				for (int i = 1; i <= cnt; i++) {
					supplierRepo.save(new Supplier(i, "Supplier" + i));
				}
			}

			if (customerRepo.findAll().isEmpty()) {
				System.out.println("Adding customers...");
				int ph = 995222;
				for (int i = 1; i <= cnt; i++) {
					Customer customer = new Customer(i, "UN" + i, "pass", "Name" + i, "Chennai");
					CustomerContact contact = new CustomerContact(String.valueOf(ph + i), "name" + i + "@mail.com");
					customer.setContact(contact);
					customerRepo.save(customer);
				}
			}

			if (itemRepo.findAll().isEmpty()) {
				System.out.println("Adding items...");
				for (int i = 1; i <= cnt; i++) {
					itemRepo.save(new Item(i, "Item" + i, i * 10));
				}
			}

			for (int i = 1; i <= cnt; i++) {
				final int idVal = i;
				new Thread(() -> {
					Customer customer = customerRepo.findById(idVal).get();
					Supplier supplier = supplierRepo.findById(idVal).get();

					List<Item> items = itemRepo.findAll();
					Set<OrderItem> orderItems = new HashSet<>();

					for (Item item : items) {
						int qty = 2;
						OrderItem oi = new OrderItem();
						oi.setItemId(item.getItemId());
						oi.setQty(qty);
						oi.setTotalPrice(qty * item.getPrice());
						orderItems.add(oi);
					}

					PaymentInfo paymentInfo = new PaymentInfo();
					paymentInfo.setBankName("MyBank");
					paymentInfo.setUsername("user" + idVal);
					paymentInfo.setPassword("pass");

					OrderRequest orderRequest = new OrderRequest();
					orderRequest.setCustomer(customer);
					orderRequest.setSupplier(supplier);
					orderRequest.setOrderItems(orderItems);
					orderRequest.setPaymentInfo(paymentInfo);

					orderService.createOrder(orderRequest);
				}).start();
			}
		};
	}
}
