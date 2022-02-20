package org.bougattaya.customerservice;

import org.bougattaya.customerservice.entities.Customer;
import org.bougattaya.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}
	
	@Bean
	CommandLineRunner start(CustomerRepository customerRepo, RepositoryRestConfiguration confRest) {
		confRest.exposeIdsFor(Customer.class);
		return args ->{
			customerRepo.save(new Customer(null,"Hatim","hatim@gmail.com"));
			customerRepo.save(new Customer(null,"Hassan","hassan@gmail.com"));
			customerRepo.save(new Customer(null,"Mohamed","mohamed@gmail.com"));
			/*
			customerRepo.findAll().forEach(c ->{
				System.out.println(c.toString());
			});
			*/
		};
	} 

}
