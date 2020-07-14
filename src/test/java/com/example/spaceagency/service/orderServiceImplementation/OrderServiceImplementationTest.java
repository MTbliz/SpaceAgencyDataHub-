package com.example.spaceagency.service.orderServiceImplementation;

import com.example.spaceagency.model.AppUser;
import com.example.spaceagency.model.CustomerOrder;
import com.example.spaceagency.model.Product;
import com.example.spaceagency.repository.OrderRepository;
import com.example.spaceagency.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class OrderServiceImplementationTest {

    private final OrderService orderService;

    @Autowired
    public OrderServiceImplementationTest(OrderService orderService) {
        this.orderService = orderService;
    }

    @MockBean
    OrderRepository orderRepository;

    @Test
    void createTest() {
        CustomerOrder customerOrder = new CustomerOrder();
        when(orderRepository.save(customerOrder)).thenReturn(customerOrder);
        Assertions.assertEquals(customerOrder, orderService.create(customerOrder));
    }

    @Test
    void readTest() {
        long id = 1;
        CustomerOrder customerOrder = new CustomerOrder();
        when(orderRepository.findById(id))
                .thenReturn(Optional.of(customerOrder));
        Assertions.assertEquals(customerOrder, orderService.read(id));
    }

    @Test
    void updateTest() {
        CustomerOrder customerOrder = new CustomerOrder();
        when(orderRepository.save(customerOrder)).thenReturn(customerOrder);
        Assertions.assertEquals(customerOrder, orderService.create(customerOrder));
    }

    @Test
    void deleteTest() {
        long id = 1;
        orderService.delete(id);
        verify(orderRepository, times(1)).deleteById((long) 1);
    }

    @Test
    void getAllOrdersTest() {
        List<CustomerOrder> customerOrders = new ArrayList<>();
        CustomerOrder customerOrder = new CustomerOrder();
        customerOrders.add(customerOrder);
        Iterable<CustomerOrder> iterable = customerOrders;
        when(orderRepository.findAll()).thenReturn(iterable);
        Assertions.assertEquals(1, ((List<CustomerOrder>) orderService.getAllOrders()).size());
    }

    @Test
    void getOrdersByCustomerTest() {
        String firstName = "Jan";
        String lastName = "Kowalski";
        AppUser customer = new AppUser(firstName, lastName);
        Set<Product> products = new HashSet<>();
        when(orderRepository.findByCustomer_LastName(customer.getLastName()))
                .thenReturn(Stream.of(new CustomerOrder(customer, products, ZonedDateTime.now())).collect(Collectors.toList()));
        Assertions.assertEquals(1, orderService.getOrdersByCustomer(firstName, lastName).size());
    }

    @Test
    void getOrdersByCustomerIdTest() {
        long id = 1;
        String firstName = "Jan";
        String lastName = "Kowalski";
        Set<Product> products = new HashSet<>();
        AppUser customer = new AppUser(firstName, lastName);
        when(orderRepository.findByCustomerId(id))
                .thenReturn(Stream.of(new CustomerOrder(customer, products, ZonedDateTime.now())).collect(Collectors.toList()));
        Assertions.assertEquals(1, orderService.getOrdersByCustomerId(id).size());
    }

}