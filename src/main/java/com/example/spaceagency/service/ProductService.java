package com.example.spaceagency.service;

import com.example.spaceagency.model.ImageryType;
import com.example.spaceagency.model.Product;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ProductService {

    Product create(Product product);

    Product read(Long productId);

    void update(Product product);

    void delete(Long productId);

    Iterable<Product> getAllProducts();

    List<Product> getProductByMissionName(String missionName);

    List<Product> getProductByType(ImageryType imageryType);

    List<Product> getProductBetweenDates(LocalDateTime startDate, LocalDateTime endDate);

    List<Product> getProductLessThenDate(LocalDateTime date);

    List<Product> getProductGreaterThenDate(LocalDateTime date);


}
