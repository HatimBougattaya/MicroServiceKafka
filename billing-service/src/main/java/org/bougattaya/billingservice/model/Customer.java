package org.bougattaya.billingservice.model;


import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.bougattaya.billingservice.entities.Bill;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data@NoArgsConstructor@AllArgsConstructor
public class Customer {
	@Id
	private Long id;
	private String name;
	private String email;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)	
	@OneToMany(mappedBy="customer")
	private Collection<Bill> bills;
	
	public Customer(CustomerModel cm) {
		this.id = cm.getId();
		this.name = cm.getName();
		this.email = cm.getEmail();
		this.bills = null;
	}
}
