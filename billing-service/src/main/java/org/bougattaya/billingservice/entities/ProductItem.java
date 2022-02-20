package org.bougattaya.billingservice.entities;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.bougattaya.billingservice.model.Product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data@NoArgsConstructor@AllArgsConstructor
public class ProductItem {
	@Id@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private double quantity;
	private double price;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@ToString.Exclude
	@ManyToOne
	Bill bill;
	
	@Transient
	private Long productId;
	
	@Transient
	private String productName;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)	
	@OneToOne
	private Product product;
	
}
