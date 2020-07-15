package com.example.spaceagency.service;

import com.example.spaceagency.exception.FileDbStorageException;
import com.example.spaceagency.exception.ProductNotFoundException;
import com.example.spaceagency.model.ImageryType;
import com.example.spaceagency.model.Product;

import java.time.ZonedDateTime;
import java.util.List;

public interface ProductService {

    Product create(Product product) throws FileDbStorageException;

    Product read(Long productId);

    Product update(Product product);

    void delete(Long productId);

    Iterable<Product> getAllProducts();

    List<Product> getAllProductsWithoutFile();

    List<Product> getProductByMissionName(String missionName);

    List<Product> getProductByType(ImageryType imageryType);

    List<Product> getProductBetweenDates(ZonedDateTime startDate, ZonedDateTime endDate);

    List<Product> getProductLessThenDate(ZonedDateTime date);

    List<Product> getProductGreaterThenDate(ZonedDateTime date);

    List<Product> getProductByFootprintCoordinate(double latitude, double longitude);

    List<Product> getMostOrderedProducts();

    Product getProductByURL(String url) throws ProductNotFoundException;

    void updateProductURL(Long id, String urlString);
}
