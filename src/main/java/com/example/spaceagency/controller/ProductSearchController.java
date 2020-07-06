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
        LOG.info("All products filtered by MissionName received.");
        return productService.getProductByMissionName(missionName);
    }

    @RequestMapping("/type")
    public List<Product> getProductsByProductType(ImageryType productType) {
        LOG.info("All products filtered by ProductType received.");
        return productService.getProductByType(productType);
    }

    @RequestMapping("/betweenDates")
    public List<Product> getProductsBetweenDates(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime endDate) {
        LOG.info("All products between startDate: " + startDate + " and endDate: " + endDate + " received.");
        return productService.getProductBetweenDates(startDate, endDate);
    }

    @RequestMapping("/greaterThenDate")
    public List<Product> getProductsGreaterThenDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime date) {
        LOG.info("All products with date greater then date: " + date + " received.");
        return productService.getProductGreaterThenDate(date);
    }

    @RequestMapping("/lessThenDate")
    public List<Product> getProductsLessThenDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime date) {
        LOG.info("All products with date less then date: " + date + " received.");
        return productService.getProductLessThenDate(date);
    }

    @RequestMapping("/footprint")
    public List<Product> getProductByCoordinate(@RequestParam(value = "latitude") double latitude, @RequestParam(value = "longitude") double longitude) {
        LOG.info("All products filtered by latitude: " + latitude + " and longitude: " + longitude + " received.");
        return productService.getProductByFootprintCoordinate(latitude, longitude);
    }

    @RequestMapping("/mostordered")
    public List<Product> getMostOrderedProduct() {
        LOG.info("Most ordered products received.");
        return productService.getMostOrderedProducts();
    }
}
