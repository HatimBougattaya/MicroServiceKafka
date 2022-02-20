package org.bougattaya.inventoryservice;

import org.bougattaya.inventoryservice.entities.Product;
import org.bougattaya.inventoryservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}
	
	@Bean
	CommandLineRunner start(ProductRepository productRepo, RepositoryRestConfiguration confRest) {
		confRest.exposeIdsFor(Product.class);
		return args ->{
			productRepo.save(new Product(null,"Product A",10,10));
			productRepo.save(new Product(null,"Product B",20,30));
			productRepo.save(new Product(null,"Product C",25,50));
		};
	}
}
