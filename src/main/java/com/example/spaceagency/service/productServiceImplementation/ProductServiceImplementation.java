package com.example.spaceagency.service.productServiceImplementation;

import com.example.spaceagency.exception.FileDbNotFoundException;
import com.example.spaceagency.exception.FileDbStorageException;
import com.example.spaceagency.exception.ProductNotFoundException;
import com.example.spaceagency.model.*;
import com.example.spaceagency.repository.ProductRepository;
import com.example.spaceagency.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Product create(Product product) throws FileDbStorageException {
        // Check if the file's name contains invalid characters
        if (product.getFileDb().getFileName().contains("..")) {
            throw new FileDbStorageException("Sorry! Filename contains invalid path sequence " + product.getFileDb().getFileName());
        }
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
    @Transactional
    public List<Product> getProductByMissionName(String missionName) {
        List<Product> products = productRepository.findByMissionName(missionName);
        return products;
    }

    @Override
    @Transactional
    public List<Product> getProductByType(ImageryType imageryType) {
        List<Product> products = productRepository.findByMission_Type(imageryType);
        return products;
    }

    @Override
    @Transactional
    public List<Product> getProductBetweenDates(ZonedDateTime startDate, ZonedDateTime endDate) {
        return productRepository.findByMissionBetween(startDate, endDate);
    }

    @Override
    @Transactional
    public List<Product> getProductLessThenDate(ZonedDateTime date) {
        return productRepository.findByMissionLessThan(date);
    }

    @Override
    @Transactional
    public List<Product> getProductGreaterThenDate(ZonedDateTime date) {
        return productRepository.findByMissionGreaterThan(date);
    }

    @Override
    @Transactional
    public List<Product> getProductByFootprintCoordinate(double latitude, double longitude) {
        List<Product> products = productRepository.findByFootprint_Coordinates(latitude, longitude);
        return products;
    }

    @Override
    @Transactional
    public List<Product> getMostOrderedProducts() {
        return productRepository.findMostOrdered();
    }

    @Override
    @Transactional
    public void updateProductURL(Long id, String urlString){
         productRepository.updateProductURL(id, urlString);
    }


    @Override
    @Transactional
    public Product getProductByURL(String url) throws ProductNotFoundException {
        return productRepository.findByUrl(url)
                .orElseThrow(() -> new ProductNotFoundException(url));
    }

    @Override
    @Transactional
    public List<Product> getAllProductsWithoutFile() {
        return productRepository.findAllProductsWithoutFile();
    }

}
