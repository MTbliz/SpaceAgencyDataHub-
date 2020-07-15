package com.example.spaceagency.controller;

import com.example.spaceagency.exception.FileDbStorageException;
import com.example.spaceagency.model.*;
import com.example.spaceagency.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.ZonedDateTime;


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
        LOG.info("All products received.");
        Iterable<Product> product = productService.getAllProducts();
        return product;
    }

    @RequestMapping("/all")
    public Iterable<Product> getAllProductsWithoutFile() {
        LOG.info("All products received.");
        return productService.getAllProductsWithoutFile();
    }

    @RequestMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        LOG.info("Product with id: " + id + " received.");
        return productService.read(id);
    }

    @PostMapping()
    public Product createProduct(@RequestParam("file") MultipartFile file,
                                 @RequestParam("mission") String missionString,
                                 @RequestParam("acquisitionDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime acquisitionDate,
                                 @RequestParam("footprint") String footprintString,
                                 @RequestParam("price") BigDecimal price

    ) throws FileDbStorageException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        Mission mission = mapper.readValue(missionString, Mission.class);
        FootprintDTO footprint = mapper.readValue(footprintString, FootprintDTO.class);
        FileDb fileDb = new FileDb(file.getOriginalFilename(), file.getContentType(), file.getBytes());
        ProductDTO productDTO = new ProductDTO(mission, acquisitionDate, footprint, price, fileDb, "");
        Product createdProduct = productService.create(productDTO.transferProductDTOtoProduct());

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("downloadFile/" + fileDb.getId() + "/" + fileDb.getFileName())
                .toUriString();

        productService.updateProductURL(createdProduct.getId(), fileDownloadUri);

        LOG.info("Product with id: " + createdProduct.getId() + " created.");
        return createdProduct;
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        LOG.info("Product with id: " + id + " deleted.");
    }
}
