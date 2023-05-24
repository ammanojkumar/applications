package io.mk.foodorder.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.mk.foodorder.dto.OrderRequest;
import io.mk.foodorder.dto.PaymentRequest;
import io.mk.foodorder.entity.Customer;
import io.mk.foodorder.entity.CustomerContact;
import io.mk.foodorder.entity.OrderItem;
import io.mk.foodorder.entity.PaymentInfo;
import io.mk.foodorder.entity.Supplier;
import io.mk.foodorder.entity.item.Item;
import io.mk.foodorder.entity.item.ItemCategory;
import io.mk.foodorder.entity.item.ItemDiscountGroup;
import io.mk.foodorder.entity.item.ItemTaxGroup;
import io.mk.foodorder.repo.CustomerRepo;
import io.mk.foodorder.repo.DiscGroupRepo;
import io.mk.foodorder.repo.ItemRepo;
import io.mk.foodorder.repo.SupplierRepo;
import io.mk.foodorder.repo.TaxGroupRepo;

@Service
public class OrderInitService {

	@Autowired
	private TaxGroupRepo taxGroupRepo;

	@Autowired
	private DiscGroupRepo discGroupRepo;

	@Autowired
	private ItemRepo itemRepo;

	@Autowired
	private SupplierRepo supplierRepo;

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private OrderService orderService;

	final int totalCusts = 5;
	final int totalSupps = 5;
	final int repeat = 1;

	public void createOrders() {
		Random random = new Random();
		for (int j = 1; j <= repeat; j++)
			for (int i = 1; i <= totalCusts; i++) {
				final int idVal = i;
				List<Item> items = itemRepo.findAll();
				Set<OrderItem> orderItems = new HashSet<>();
				int cnt = random.nextInt(items.size());
				cnt = cnt == 0 ? 1 : cnt;
				for (int k = 0; k < cnt; k++) {
					Item item = items.get(k);
					int qty = random.nextInt(4);
					qty = qty == 0 ? 1 : qty;
					OrderItem oi = new OrderItem();
					oi.setItem(item);
					oi.setQty(qty);
					oi.setPrice(qty * item.getPrice());
					orderItems.add(oi);
				}

				OrderRequest orderRequest = new OrderRequest();
				orderRequest.setCustId(idVal);
				orderRequest.setOrderItems(orderItems);

				orderService.createOrder(orderRequest);
			}
	}

	public void addCustAndSupp() {
		if (supplierRepo.findAll().isEmpty()) {
			System.out.println("Adding suppliers...");
			for (int i = 1; i <= totalSupps; i++) {
				supplierRepo.save(new Supplier(i, "SUPPLIER" + i));
			}
		}
		;
		Queue<String> custNames = new LinkedList<>(
				Arrays.asList(new String[] { "Arun", "Bala", "Chitra", "Dany", "Gokul" }));

		if (customerRepo.findAll().isEmpty()) {
			System.out.println("Adding customers...");
			int ph = 995222;
			for (int i = 1; i <= totalCusts; i++) {
				Customer customer = new Customer(i, "cust" + i, "pass", custNames.peek(), "Chennai");
				CustomerContact contact = new CustomerContact(String.valueOf(ph + i),
						custNames.poll() + i + "@mail.com");
				customer.setContact(contact);
				customerRepo.save(customer);
			}
		}
	}

	public void addItems() {
		String taxGroupsJson = "[{\"taxPercent\":6.0}, {\"taxPercent\":8.0}, {\"taxPercent\":12.0}]";
		String discGroupsJson = "[{\"discountPercent\":1.0}, {\"discountPercent\":2.0}, {\"discountPercent\":4.0}]";

		try {
			ItemTaxGroup[] taxGroups = new ObjectMapper().readValue(taxGroupsJson.getBytes(), ItemTaxGroup[].class);
			taxGroupRepo.saveAll(Arrays.asList(taxGroups));

			ItemDiscountGroup[] discGroups = new ObjectMapper().readValue(discGroupsJson.getBytes(),
					ItemDiscountGroup[].class);
			discGroupRepo.saveAll(Arrays.asList(discGroups));

			Item item1 = new Item(null, "Idly", 30, taxGroups[0], discGroups[0], ItemCategory.MAIN);
			Item item2 = new Item(null, "Dosai", 40, taxGroups[0], discGroups[0], ItemCategory.MAIN);

			Item item3 = new Item(null, "Soup", 15, taxGroups[1], discGroups[2], ItemCategory.STARTERS);
			Item item4 = new Item(null, "Vadai", 8, taxGroups[1], discGroups[2], ItemCategory.STARTERS);

			Item item5 = new Item(null, "Coffee", 16, taxGroups[2], discGroups[1], ItemCategory.DRINK);
			Item item6 = new Item(null, "Tea", 12, taxGroups[2], discGroups[1], ItemCategory.DRINK);
			itemRepo.saveAll(new ArrayList<Item>(Arrays.asList(item1, item2, item3, item4, item5, item6)));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void makePayment() {
		customerRepo.findAll().forEach(cus -> {
			cus.getOrders().forEach(ord -> {
				PaymentRequest paymentRequest = new PaymentRequest();
				paymentRequest.setCustId(cus.getId());
				paymentRequest.setOrderId(ord.getId());

				PaymentInfo paymentInfo = new PaymentInfo();
				paymentRequest.setPaymentInfo(paymentInfo);
				paymentInfo.setCardNo("1111" + cus.getId());
				orderService.makePayment(ord, paymentRequest);
			});
		});
	}
}
