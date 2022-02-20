package org.bougattaya.customerservice.web;

import java.util.List;

import org.bougattaya.customerservice.entities.Customer;
import org.bougattaya.customerservice.repository.CustomerRepository;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CustomerController {
	
	private StreamBridge streamBridge;
	private CustomerRepository custRepo;
	
	public CustomerController(StreamBridge sb, CustomerRepository cr) {
		this.streamBridge = sb;
		this.custRepo = cr;
	}
	
	//TOPIC WOULD BE : CUSTOMER_TOPIC
	@GetMapping(path = "/publishCustomer/{topic}/{id}")
	public Customer publishKafkaCustomer(@PathVariable(name="topic") String topic,
								@PathVariable(name = "id") Long id) {
		//get
		Customer c = custRepo.findById(id).get();
		//send
		streamBridge.send(topic, c);
		return c;
	}
	
	//MAYBE NEXT UPDATE
	//still needs listener 
	@GetMapping(path = "/publishCustomers/{topic}")
	public List<Customer> publishKafkaCustomerS(@PathVariable(name="topic") String topic) {
		//get
		List<Customer> c = custRepo.findAll();
		//send
		streamBridge.send(topic, c);
		return c;
	}
}
