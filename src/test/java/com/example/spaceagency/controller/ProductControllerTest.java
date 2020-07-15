package com.example.spaceagency.controller;

import com.example.spaceagency.model.*;
import com.example.spaceagency.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @MockBean
    private ProductService productService;

    private MockMvc mockMvc;

    @Autowired
    ProductControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void getAllProductsTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Product product1 = new Product();
        Product product2 = new Product();
        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        when(productService.getAllProducts()).thenReturn(products);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/products"))
                .andExpect(status().isOk())
                .andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        Assertions.assertEquals(objectMapper.writeValueAsString(products), response);
    }

    @Test
    void getAllProductsWithoutFileTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Product product1 = new Product();
        Product product2 = new Product();
        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        when(productService.getAllProductsWithoutFile()).thenReturn(products);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/products/all"))
                .andExpect(status().isOk())
                .andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        Assertions.assertEquals(objectMapper.writeValueAsString(products), response);
    }

    @Test
    void getProductTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Product product = new Product();
        when(productService.read((long) 1)).thenReturn(product);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/products/1"))
                .andExpect(status().isOk())
                .andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        Assertions.assertEquals(objectMapper.writeValueAsString(product), response);
    }

    @Test
    void createProductTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        byte[] fileContent = "bar".getBytes(StandardCharsets.UTF_8);
        MockMultipartFile filePart = new MockMultipartFile("file", "orig", null, fileContent);
        FileDb fileDb = new FileDb("Test File", filePart.getContentType(), filePart.getBytes());

        Product product = new Product(new Mission(), ZonedDateTime.parse("2016-09-13T22:30:52.123+02:00"),
                new Footprint(), BigDecimal.valueOf(1000), fileDb, "url");

        when(productService.create(any(Product.class))).thenReturn(product);
        MvcResult result = mockMvc.perform(multipart("/products")
                .file(filePart)
                .param("mission", objectMapper.writeValueAsString(new Mission()))
                .param("acquisitionDate", "")
                .param("footprint", objectMapper.writeValueAsString(new FootprintDTO()))
                .param("price", "1000")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.price").value("1000"))
                .andReturn();
    }

    @Test
    void deleteProductTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/products/1"))
                .andExpect(status().isOk())
                .andReturn();
    }
}