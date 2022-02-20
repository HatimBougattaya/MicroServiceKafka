package org.bougattaya.billingservice.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.bougattaya.billingservice.entities.ProductItem;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data@NoArgsConstructor@AllArgsConstructor
public class Product {
	@Id
	private Long id;
	private String name;
	private double price;
	private double quantity;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)	
	@OneToOne(mappedBy="product")
	private ProductItem productItem;
	
	public Product(ProductModel pm) {
		this.id = pm.getId();
		this.name = pm.getName();
		this.price = pm.getPrice();
		this.quantity = pm.getQuantity();
		this.productItem = null;
	}
}
