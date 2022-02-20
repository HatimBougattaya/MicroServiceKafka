package org.bougattaya.billingservice.repository;

import org.bougattaya.billingservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long>{

}
