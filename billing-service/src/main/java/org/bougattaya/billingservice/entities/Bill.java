package org.bougattaya.billingservice.entities;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.bougattaya.billingservice.model.Customer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Data@NoArgsConstructor@AllArgsConstructor
public class Bill {
	@Id@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date billingDate;
	
	@OneToMany(mappedBy="bill")
	private Collection<ProductItem> productItems;
	
	/*
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Long customerId;
	*/
	@ManyToOne
	private Customer customer;
}
