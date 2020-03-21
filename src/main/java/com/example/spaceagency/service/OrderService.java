package com.example.spaceagency.service;

import com.example.spaceagency.model.CustomerOrder;
import java.util.List;

public interface OrderService {

    CustomerOrder create(CustomerOrder customerOrder);

    CustomerOrder read(Long customerOderId);

    CustomerOrder update(CustomerOrder customerOrder);

    void delete(Long customerOrderId);

    Iterable<CustomerOrder> getAllOrders();

    List<CustomerOrder> getOrdersByCustomer(String firstName, String lastName);
}
