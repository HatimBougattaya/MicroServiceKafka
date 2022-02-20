package org.bougattaya.billingservice.model;

import lombok.Data;

@Data
public class ProductModel {
	private Long id;
	private String name;
	private double price;
	private double quantity;
}
