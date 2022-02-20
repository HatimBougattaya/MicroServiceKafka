package org.bougattaya.billingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingServiceApplication.class, args);
	}

	//INITS ARE NOW PROCESSED ASYNC WITH KAFKA 
	//NO NEEDS FOR CODE BELOW ANYMORE 
/*
	@Bean
	CommandLineRunner start(BillRepository billRepo, ProductItemRepository productItemRepo){
		return args -> {
			
//MODELS ARE ALREADY SERIALIZED WITH THE BILL
			//init model
			//Customer c = customerClient.getCustomerById(1L);
			//PagedModel<Product> productPagedModel = productItemClient.pageProducts(0,5);
			
//BILL ALREADY IN DATABSE
			
			//init entities
			//Bill firstBill = billRepo.save(new Bill(null, new Date(), null, c.getId(), null));
			
			productPagedModel.forEach(p ->{
				ProductItem productItem = new ProductItem();
				productItem.setPrice(p.getPrice());
				productItem.setQuantity(1+ new Random().nextInt(50));
				productItem.setBill(firstBill);
				productItem.setProductId(p.getId());
				productItemRepo.save(productItem);
			});
			
		};
	}
*/
	
} 
