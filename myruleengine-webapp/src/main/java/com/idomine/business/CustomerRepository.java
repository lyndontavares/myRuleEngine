package com.idomine.business;

import java.util.List;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import com.idomine.model.Customer;

@Repository(forEntity = Customer.class)
public interface CustomerRepository  extends EntityRepository <Customer, Long> {
    
   List<Customer> findByName(String name);
    
}