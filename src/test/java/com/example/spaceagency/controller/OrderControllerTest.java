package com.example.spaceagency.controller;

import com.example.spaceagency.model.*;
import com.example.spaceagency.service.AppUserService;
import com.example.spaceagency.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {

    @MockBean
    private OrderService orderService;

    private MockMvc mockMvc;

    @Autowired
    OrderControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void getAllOrdersTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Set<Product> products = new HashSet<>();
        CustomerOrder customerOrder1 = new CustomerOrder(new AppUser(), products, null);
        CustomerOrder customerOrder2 = new CustomerOrder(new AppUser(), products, null);
        List<CustomerOrder> customerOrders = new ArrayList<>();
        customerOrders.add(customerOrder1);
        customerOrders.add(customerOrder2);
        when(orderService.getAllOrders()).thenReturn(customerOrders);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/orders"))
                .andExpect(status().isOk())
                .andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        Assertions.assertEquals(objectMapper.writeValueAsString(customerOrders), response);
    }

    @Test
    void getOrdersByCustomerTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Set<Product> products = new HashSet<>();
        CustomerOrder customerOrder1 = new CustomerOrder(new AppUser(), products, null);
        CustomerOrder customerOrder2 = new CustomerOrder(new AppUser(), products, null);
        List<CustomerOrder> customerOrders = new ArrayList<>();
        customerOrders.add(customerOrder1);
        customerOrders.add(customerOrder2);
        when(orderService.getOrdersByCustomer("Jan", "Kowalski")).thenReturn(customerOrders);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/orders/search/customer")
                .param("firstName", "Jan")
                .param("lastName", "Kowalski"))
                .andExpect(status().isOk())
                .andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        Assertions.assertEquals(objectMapper.writeValueAsString(customerOrders), response);
    }

    @Test
    void getOrderByCustomerIdTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Set<Product> products = new HashSet<>();
        CustomerOrder customerOrder1 = new CustomerOrder(new AppUser(), products, null);
        CustomerOrder customerOrder2 = new CustomerOrder(new AppUser(), products, null);
        List<CustomerOrder> customerOrders = new ArrayList<>();
        when(orderService.getOrdersByCustomerId((long) 1)).thenReturn(customerOrders);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/orders/myorders/1"))
                .andExpect(status().isOk())
                .andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        Assertions.assertEquals(objectMapper.writeValueAsString(customerOrders), response);
    }

    @Test
    void createOrderTest() throws Exception {

    }
}