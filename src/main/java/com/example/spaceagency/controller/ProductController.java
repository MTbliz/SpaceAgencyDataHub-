package com.example.spaceagency.controller;

import com.example.spaceagency.model.Product;
import com.example.spaceagency.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping()
    public Iterable<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @RequestMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productService.read(id);
    }

    @PostMapping()
    public String createProduct(@RequestBody Product product) {
        productService.create(product);
        return "Product created";
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return "Product deleted";
    }
}
