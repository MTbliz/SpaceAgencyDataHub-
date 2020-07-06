package com.example.spaceagency.model;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProductDTOTest {

    @Test
    void transferProductDTOtoProductTest() {

        ZonedDateTime date = ZonedDateTime.now();
        Coordinate coordinate1 = new Coordinate(1, 2);
        Coordinate coordinate2 = new Coordinate(1, 2);
        Coordinate coordinate3 = new Coordinate(1, 2);
        Coordinate coordinate4 = new Coordinate(1, 2);

        //ProductDTO productDTO = new ProductDTO(new Mission(), date, new FootprintDTO(coordinate1, coordinate2, coordinate3, coordinate4), new BigDecimal(1000), "tesURL");
       // Product product = new Product(new Mission(), date, new Footprint(), new BigDecimal(1000), "tesURL");

        ProductDTO productDTO = new ProductDTO(new Mission(), date, new FootprintDTO(coordinate1, coordinate2, coordinate3, coordinate4), new BigDecimal(1000), new FileDb(), "tesURL");
         Product product = new Product(new Mission(), date, new Footprint(), new BigDecimal(1000), new FileDb(), "tesURL");

        Product result = productDTO.transferProductDTOtoProduct();

        Assertions.assertEquals(product.getAcquisitionDate(), result.getAcquisitionDate());
        Assertions.assertEquals(4, result.getFootprint().getCoordinates().size());
        Assertions.assertEquals(product.getPrice(), result.getPrice());
        Assertions.assertEquals(product.getUrl(), result.getUrl());
    }
}