package com.example.spaceagency.controller;

import com.example.spaceagency.model.ImageryType;
import com.example.spaceagency.model.Product;
import com.example.spaceagency.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequestMapping("/search/products")
public class ProductSearchController {

    private static final Logger LOG = LogManager.getLogger(ProductSearchController.class);

    private final ProductService productService;

    @Autowired
    public ProductSearchController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/missionName")
    public List<Product> getProductsByMissionName(@RequestParam(value = "missionName") String missionName) {
        LOG.info("Info log in our getProductsByMissionName method");
        return productService.getProductByMissionName(missionName);
    }

    @RequestMapping("/type")
    public List<Product> getProductsByProductType(ImageryType productType) {
        LOG.info("Info log in our getProductsByProductType method");
        return productService.getProductByType(productType);
    }

    @RequestMapping("/betweenDates")
    public List<Product> getProductsBetweenDates(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime endDate) {
        LOG.info("Info log in our getProductsBetweenDates method");
        return productService.getProductBetweenDates(startDate, endDate);
    }

    @RequestMapping("/greaterThenDate")
    public List<Product> getProductsGreaterThenDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime date) {
        LOG.info("Info log in our getProductsGreaterThenDate method");
        return productService.getProductGreaterThenDate(date);
    }

    @RequestMapping("/lessThenDate")
    public List<Product> getProductsLessThenDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime date) {
        LOG.info("Info log in our getProductsLessThenDate method");
        return productService.getProductLessThenDate(date);
    }

    @RequestMapping("/footprint")
    public List<Product> getProductByCoordinate(@RequestParam(value = "latitude") double latitude, @RequestParam(value = "longitude") double longitude) {
        LOG.info("Info log in our getProductByCoordinate method");
        return productService.getProductByFootprintCoordinate(latitude, longitude);
    }

    @RequestMapping("/mostordered")
    public List<Product> getMostOrderedProduct() {
        LOG.info("Info log in our getMostOrderedProduct method");
        return productService.getMostOrderedProducts();
    }
}
