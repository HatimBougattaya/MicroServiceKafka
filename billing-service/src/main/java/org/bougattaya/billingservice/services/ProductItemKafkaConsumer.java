package org.bougattaya.billingservice.services;

import java.util.Date;
import java.util.Random;
import java.util.function.Consumer;

import org.bougattaya.billingservice.entities.Bill;
import org.bougattaya.billingservice.entities.ProductItem;
import org.bougattaya.billingservice.model.Product;
import org.bougattaya.billingservice.model.ProductModel;
import org.bougattaya.billingservice.repository.BillRepository;
import org.bougattaya.billingservice.repository.ProductItemRepository;
import org.bougattaya.billingservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


@Service
public class ProductItemKafkaConsumer {
	@Autowired
	private ProductItemRepository piRepo;
	@Autowired
	private BillRepository billRepo;
	@Autowired 
	private ProductRepository productRepo;
	
	//Getting a page of products
	@Bean
	public Consumer<Page<ProductModel>> productConsumer(){
		return (input)->{
			System.out.println("INPUT ::::: "+input.toString());
			input.forEach(p ->{
				//SAVE product
				System.out.println("MODEL ::::: "+p.toString());
				Product pNom = new Product(p);
				System.out.println("PERSIST ::::: "+pNom.toString());
				productRepo.save(pNom);
				
				//INIT productItem
				ProductItem productItem = new ProductItem();
				productItem.setPrice(p.getPrice());
				productItem.setQuantity(1+ new Random().nextInt(50));
				
				//SET Bill and INIT if necessary
				if(billRepo.findById(1L).isPresent()) {
					productItem.setBill(billRepo.findById(1L).get());
				}else {
					billRepo.save(new Bill(null, new Date(), null, null));
					productItem.setBill(billRepo.findById(1L).get());
				}
				//these are transient
				//productItem.setProductId(p.getId());
				//productItem.setProductName(p.getName());
				
				//SAVE entity
				piRepo.save(productItem);
				
				//merge
				productItem.setProduct(pNom);
				
				//debug
				//System.out.println("PRODUCT IS :::    "+p);
			});
		};
	}
}
