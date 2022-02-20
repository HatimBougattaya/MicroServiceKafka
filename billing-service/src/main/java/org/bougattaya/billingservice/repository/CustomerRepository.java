package org.bougattaya.billingservice.repository;

import org.bougattaya.billingservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

}
