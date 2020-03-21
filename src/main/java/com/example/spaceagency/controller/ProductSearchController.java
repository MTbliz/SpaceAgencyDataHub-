package com.example.spaceagency.controller;

import com.example.spaceagency.model.ImageryType;
import com.example.spaceagency.model.Product;
import com.example.spaceagency.service.ProductService;
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

    private final ProductService productService;

    @Autowired
    public ProductSearchController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/missionName")
    public List<Product> getProductsByMissionName(@RequestParam(value = "missionName") String missionName) {
        return productService.getProductByMissionName(missionName);
    }

    @RequestMapping("/type")
    public List<Product> getProductsByProductType(ImageryType productType) {
        return productService.getProductByType(productType);
    }

    @RequestMapping("/betweenDates")
    public List<Product> getProductsBetweenDates(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime endDate) {
        return productService.getProductBetweenDates(startDate, endDate);
    }

    @RequestMapping("/greaterThenDate")
    public List<Product> getProductsGreaterThenDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime date) {
        return productService.getProductGreaterThenDate(date);
    }

    @RequestMapping("/lessThenDate")
    public List<Product> getProductsLessThenDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime date) {
        return productService.getProductLessThenDate(date);
    }

    @RequestMapping("/footprint")
    public List<Product> getProductByCoordinate(@RequestParam(value = "latitude") double latitude, @RequestParam(value = "longitude") double longitude) {
        return productService.getProductByFootprintCoordinate(latitude, longitude);
    }
}
