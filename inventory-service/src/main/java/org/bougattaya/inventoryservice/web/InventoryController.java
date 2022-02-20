package org.bougattaya.inventoryservice.web;

import org.bougattaya.inventoryservice.entities.Product;
import org.bougattaya.inventoryservice.repository.ProductRepository;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryController {
	
	private StreamBridge streamBridge;
	private ProductRepository productRepo;
	//@Autowired
	public InventoryController(StreamBridge sb, ProductRepository pr ) {
		this.streamBridge = sb;
		this.productRepo = pr;
	}
	
	//TOPIC WOULD BE : PRODUCT_TOPIC
	
	//Page of requested products
	@GetMapping(path = "/publishProducts/{topic}")
	public Page<Product> publishKafkaProduct(@PathVariable(name="topic") String topic,
			@RequestParam(name ="page") int page
			, @RequestParam(name = "size") int size) {
		
		//get
		Page<Product> pgp = productRepo.findAll(PageRequest.of(page, size));
		//send
		pgp.forEach(p ->{
			streamBridge.send(topic, p);
		});
		return pgp;
	}
	
	//MAYBE NEXT UPDATE
	//Listener needs to be implemented
	//One product
	@GetMapping(path = "/publishProduct/{topic}/{id}")
	public Product publishKafkaProducts(@PathVariable(name="topic") String topic,
			@PathVariable(name = "id") Long id) {
		//get
		Product p = productRepo.findById(id).get();
		//send
		streamBridge.send(topic, p);
		return p;
	}
}
