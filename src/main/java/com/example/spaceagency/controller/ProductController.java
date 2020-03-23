package com.example.spaceagency.controller;

import com.example.spaceagency.model.Product;
import com.example.spaceagency.model.ProductDTO;
import com.example.spaceagency.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private static final Logger LOG = LogManager.getLogger(ProductController.class);

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping()
    public Iterable<Product> getAllProducts() {
        LOG.info("Info log in our getAllProducts method");
        return productService.getAllProducts();
    }

    @RequestMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        LOG.info("Info log in our getProduct method");
        return productService.read(id);
    }

    @PostMapping()
    public String createProduct(@RequestBody ProductDTO productDTO) {

        productService.create(productDTO.transferProductDTOtoProduct());
        LOG.info("Info log in our createProduct method");
        return "Product created";
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        LOG.info("Info log in our deleteProduct method");
        return "Product deleted";
    }
}
