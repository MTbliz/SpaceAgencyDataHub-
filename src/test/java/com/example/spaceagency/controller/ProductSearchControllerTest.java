package com.example.spaceagency.controller;

import com.example.spaceagency.model.ImageryType;
import com.example.spaceagency.model.Product;
import com.example.spaceagency.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class ProductSearchControllerTest {

    @MockBean
    private ProductService productService;

    private MockMvc mockMvc;

    @Autowired
    ProductSearchControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void getProductsByMissionNameTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Product product1 = new Product();
        Product product2 = new Product();
        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        when(productService.getProductByMissionName("Mission1")).thenReturn(products);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/search/products/missionName")
                .param("missionName", "Mission1"))
                .andExpect(status().isOk())
                .andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        Assertions.assertEquals(objectMapper.writeValueAsString(products), response);
    }

    @Test
    void getProductsByProductTypeTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Product product1 = new Product();
        Product product2 = new Product();
        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        when(productService.getProductByType(ImageryType.TYPE_HYPERSPECTRAL)).thenReturn(products);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/search/products/type")
                .param("productType", "TYPE_HYPERSPECTRAL"))
                .andExpect(status().isOk())
                .andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        Assertions.assertEquals(objectMapper.writeValueAsString(products), response);
    }

    @Test
    void getProductsBetweenDatesTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Product product1 = new Product();
        Product product2 = new Product();
        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        when(productService.getProductBetweenDates(any(ZonedDateTime.class), any(ZonedDateTime.class))).thenReturn(products);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/search/products/betweenDates")
                .param("startDate", "2016-09-13T22:30:52.123+02:00")
                .param("endDate", "2016-09-13T22:30:52.123+02:00")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        Assertions.assertEquals(objectMapper.writeValueAsString(products), response);
    }

    @Test
    void getProductsGreaterThenDateTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Product product1 = new Product();
        Product product2 = new Product();
        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        when(productService.getProductGreaterThenDate(any(ZonedDateTime.class))).thenReturn(products);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/search/products/greaterThenDate")
                .param("date", "2016-09-13T22:30:52.123+02:00")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        Assertions.assertEquals(objectMapper.writeValueAsString(products), response);
    }

    @Test
    void getProductsLessThenDateTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Product product1 = new Product();
        Product product2 = new Product();
        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        when(productService.getProductLessThenDate(any(ZonedDateTime.class))).thenReturn(products);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/search/products/lessThenDate")
                .param("date", "2016-09-13T22:30:52.123+02:00")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        Assertions.assertEquals(objectMapper.writeValueAsString(products), response);
    }

    @Test
    void getProductByCoordinateTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Product product1 = new Product();
        Product product2 = new Product();
        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        when(productService.getProductByFootprintCoordinate(0.1, 0.1)).thenReturn(products);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/search/products/footprint")
                .param("latitude", "0.1")
                .param("longitude", "0.1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        Assertions.assertEquals(objectMapper.writeValueAsString(products), response);
    }

    @Test
    void getMostOrderedProductTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Product product1 = new Product();
        Product product2 = new Product();
        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        when(productService.getMostOrderedProducts()).thenReturn(products);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/search/products/mostordered"))
                .andExpect(status().isOk())
                .andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        Assertions.assertEquals(objectMapper.writeValueAsString(products), response);
    }
}