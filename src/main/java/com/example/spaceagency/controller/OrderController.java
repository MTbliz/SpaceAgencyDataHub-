package com.example.spaceagency.controller;

import com.example.spaceagency.model.CustomerOrder;
import com.example.spaceagency.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping()
    public Iterable<CustomerOrder> getAllOrders() {
        return orderService.getAllOrders();
    }

    @RequestMapping("/search/customer")
    public List<CustomerOrder> getOrdersByCustomer(@RequestParam String firstName, @RequestParam String lastName) {
        return orderService.getOrdersByCustomer(firstName, lastName);
    }

    @PostMapping("/create")
    public String createOrder(@RequestBody CustomerOrder customerOrder) {
        orderService.create(customerOrder);
        return "Order Created";
    }
}
