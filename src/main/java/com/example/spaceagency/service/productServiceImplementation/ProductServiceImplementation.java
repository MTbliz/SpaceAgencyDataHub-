package com.example.spaceagency.service.productServiceImplementation;

import com.example.spaceagency.model.ImageryType;
import com.example.spaceagency.model.Product;
import com.example.spaceagency.repository.ProductRepository;
import com.example.spaceagency.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImplementation implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImplementation(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product read(Long productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        return productOptional.get();
    }

    @Override
    public Product update(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductByMissionName(String missionName) {
        List<Product> products = productRepository.findByMissionName(missionName);
        return products;
    }

    @Override
    public List<Product> getProductByType(ImageryType imageryType) {
        List<Product> products = productRepository.findByMission_Type(imageryType);
        return products;
    }

    @Override
    public List<Product> getProductBetweenDates(ZonedDateTime startDate, ZonedDateTime endDate) {
        return productRepository.findByMissionBetween(startDate, endDate);
    }

    @Override
    public List<Product> getProductLessThenDate(ZonedDateTime date) {
        return productRepository.findByMissionLessThan(date);
    }

    @Override
    public List<Product> getProductGreaterThenDate(ZonedDateTime date) {
        return productRepository.findByMissionGreaterThan(date);
    }
}
