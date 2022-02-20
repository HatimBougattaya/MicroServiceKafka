package org.bougattaya.billingservice.web;

import org.bougattaya.billingservice.entities.Bill;
import org.bougattaya.billingservice.model.Product;
import org.bougattaya.billingservice.repository.BillRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillingController {
	
	private BillRepository billRepo;
	
	public BillingController(BillRepository br) {
		this.billRepo = br;
	}
	
	@GetMapping(path = "/fullBill/{id}")
	public Bill getBill(@PathVariable(name="id") Long id) {
		//Should be done in a service // JUST TESTING FOR NOW 
		//init transient 
		billRepo.findById(id).get().getProductItems().forEach(p ->{
			Product pModel = p.getProduct();
			p.setProductId(pModel.getId());
			p.setProductName(pModel.getName());
		});
		
		return billRepo.findById(id).get();
	}
}
