package com.example.spaceagency.repository;

import com.example.spaceagency.model.AppUser;
import com.example.spaceagency.model.CustomerOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<CustomerOrder, Long> {

    List<CustomerOrder> findAllByCustomer(AppUser customer);

    List<CustomerOrder> findByCustomer_LastName(String lastName);

    @Query( value = "SELECT order_id, order_date, app_user_id FROM customer_order where app_user_id= :id", nativeQuery = true)
    List<CustomerOrder> findByCustomerId(Long id);
}
