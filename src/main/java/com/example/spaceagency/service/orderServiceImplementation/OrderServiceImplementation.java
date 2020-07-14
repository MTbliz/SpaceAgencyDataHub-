package com.example.spaceagency.service.orderServiceImplementation;

import com.example.spaceagency.model.CustomerOrder;
import com.example.spaceagency.repository.OrderRepository;
import com.example.spaceagency.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImplementation implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImplementation(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public CustomerOrder create(CustomerOrder customerOrder) {
        return orderRepository.save(customerOrder);
    }

    @Override
    public CustomerOrder read(Long customerOderId) {
        Optional<CustomerOrder> orderOptional = orderRepository.findById(customerOderId);
        return orderOptional.get();
    }

    @Override
    public CustomerOrder update(CustomerOrder customerOrder) {
        return orderRepository.save(customerOrder);
    }

    @Override
    public void delete(Long customerOrderId) {
        orderRepository.deleteById(customerOrderId);
    }

    @Override
    public Iterable<CustomerOrder> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    @Transactional
    public List<CustomerOrder> getOrdersByCustomer(String firstName, String lastName) {
        List<CustomerOrder> orders = orderRepository.findByCustomer_LastName(lastName);
        List<CustomerOrder> filteredOrders = orders.stream()
                .filter(order -> order.getCustomer().getFirstName().equals(firstName) && order.getCustomer().getLastName().equals(lastName))
                .collect(Collectors.toList());
        filteredOrders.sort(Comparator.comparing(order -> order.getOrderDate()));
        return filteredOrders;
    }

    @Override
    @Transactional
    public List<CustomerOrder> getOrdersByCustomerId(Long id) {
        List<CustomerOrder> orders = orderRepository.findByCustomerId(id);
        orders.sort(Comparator.comparing(order -> order.getOrderDate()));
        return orders;
    }

}
