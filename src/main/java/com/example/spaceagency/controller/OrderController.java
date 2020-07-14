package com.example.spaceagency.controller;

import com.example.spaceagency.model.CustomerOrder;
import com.example.spaceagency.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private static final Logger LOG = LogManager.getLogger(OrderController.class);

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping()
    public Iterable<CustomerOrder> getAllOrders() {
        LOG.info("All orders received.");
        return orderService.getAllOrders();
    }

    @RequestMapping("/search/customer")
    public List<CustomerOrder> getOrdersByCustomer(@RequestParam String firstName, @RequestParam String lastName) {
        LOG.info("All orders for customer received.");
        return orderService.getOrdersByCustomer(firstName, lastName);
    }

    @RequestMapping("/myorders/{id}")
    public List<CustomerOrder> getOrderByCustomerId(@PathVariable Long id) {
        return orderService.getOrdersByCustomerId(id);
    }

    @PostMapping()
    public CustomerOrder createOrder(@RequestBody CustomerOrder customerOrder) {
        CustomerOrder createdOrder = orderService.create(customerOrder);
        LOG.info("Order with id: " + createdOrder.getId() +  " created.");
        return createdOrder;
    }
}
