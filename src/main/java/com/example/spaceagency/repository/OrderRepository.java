package com.example.spaceagency.repository;

import com.example.spaceagency.model.Customer;
import com.example.spaceagency.model.CustomerOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<CustomerOrder, Long> {

    List<CustomerOrder> findAllByCustomer(Customer customer);

    List<CustomerOrder> findByCustomer_LastName(String lastName);
}
