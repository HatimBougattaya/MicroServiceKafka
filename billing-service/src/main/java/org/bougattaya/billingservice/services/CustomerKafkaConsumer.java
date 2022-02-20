package org.bougattaya.billingservice.services;

import java.util.Date;
import java.util.function.Consumer;

import org.bougattaya.billingservice.entities.Bill;
import org.bougattaya.billingservice.model.Customer;
import org.bougattaya.billingservice.model.CustomerModel;
import org.bougattaya.billingservice.repository.BillRepository;
import org.bougattaya.billingservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class CustomerKafkaConsumer {
	
	@Autowired
	private BillRepository billRepo; 
	@Autowired
	private CustomerRepository customerRepo;
	@Bean
	public Consumer<CustomerModel> customerConsumer(){
		return (input)->{
			//input is transient so : ABSENT IN DB BUT PRESENT IN SERIALISED OBJECT
			
			//CHECK and INIT Bill
			Bill firstBill;
			if(billRepo.findById(1L).isPresent()) {
				firstBill= billRepo.findById(1L).get();
			}else {
				firstBill = billRepo.save(new Bill(null, new Date(), null, null));
			}
			//INIT and SAVE Customer
			Customer customer = customerRepo.save(new Customer(input));
			//MERGE
			firstBill.setCustomer(customer);
		};
	}
}
