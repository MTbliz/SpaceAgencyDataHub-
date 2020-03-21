package com.example.spaceagency.service.productServiceImplementation;

import com.example.spaceagency.model.CustomerOrder;
import com.example.spaceagency.model.ImageryType;
import com.example.spaceagency.model.Product;
import com.example.spaceagency.repository.OrderRepository;
import com.example.spaceagency.repository.ProductRepository;
import com.example.spaceagency.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProductServiceImplementationTest {

    private final ProductService productService;

    @Autowired
    public ProductServiceImplementationTest(ProductService productService) {
        this.productService = productService;
    }

    @MockBean
    ProductRepository productRepository;

    @Test
    void createTest() {
        Product product = new Product();
        when(productRepository.save(product)).thenReturn(product);
        Assertions.assertEquals(product,productService.create(product));
    }

    @Test
    void readTest() {
        long id = 1;
        Product product = new Product();
        when(productRepository.findById(id))
                .thenReturn(Optional.of(product));
        Assertions.assertEquals(product, productService.read(id));
    }

    @Test
    void updateTest() {
        Product product = new Product();
        when(productRepository.save(product)).thenReturn(product);
        Assertions.assertEquals(product,productService.create(product));
    }

    @Test
    void deleteTest() {
        long id = 1;
        productService.delete(id);
        verify(productRepository,times(1)).deleteById((long) 1);
    }

    @Test
    void getAllProductsTest() {
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        products.add(product);
        Iterable<Product> iterable = products;
        when(productRepository.findAll()).thenReturn(iterable);
        Assertions.assertEquals(1,((List<Product>) productService.getAllProducts()).size());
    }

    @Test
    void getProductByMissionNameTest() {
        String missionName = "missionName";
        when(productRepository.findByMissionName(missionName))
                .thenReturn(Stream.of(new Product()).collect(Collectors.toList()));
        Assertions.assertEquals(1, productService.getProductByMissionName(missionName).size());
    }

    @Test
    void getProductByTypeTest() {
        when(productRepository.findByMission_Type(ImageryType.TYPE_HYPERSPECTRAL))
                .thenReturn(Stream.of(new Product()).collect(Collectors.toList()));
        Assertions.assertEquals(1, productService.getProductByType(ImageryType.TYPE_HYPERSPECTRAL).size());
    }

    @Test
    void getProductBetweenDatesTest() {
        ZonedDateTime startDate = ZonedDateTime.now();
        ZonedDateTime endDate = ZonedDateTime.now().plusDays(1);
        when(productRepository.findByMissionBetween(startDate,endDate))
                .thenReturn(Stream.of(new Product()).collect(Collectors.toList()));
        Assertions.assertEquals(1, productService.getProductBetweenDates(startDate,endDate).size());
    }

    @Test
    void getProductLessThenDateTest() {
        ZonedDateTime date = ZonedDateTime.now();
        when(productRepository.findByMissionLessThan(date))
                .thenReturn(Stream.of(new Product()).collect(Collectors.toList()));
        Assertions.assertEquals(1, productService.getProductLessThenDate(date).size());
    }

    @Test
    void getProductGreaterThenDateTest() {
        ZonedDateTime date = ZonedDateTime.now();
        when(productRepository.findByMissionGreaterThan(date))
                .thenReturn(Stream.of(new Product()).collect(Collectors.toList()));
        Assertions.assertEquals(1, productService.getProductGreaterThenDate(date).size());
    }

    @Test
    void getProductByFootprintCoordinateTest(){
        double latitude = 1.0;
        double longitude = 1.0;
        when(productRepository.findByFootprint_Coordinates(latitude,longitude))
                .thenReturn(Stream.of(new Product()).collect(Collectors.toList()));
        Assertions.assertEquals(1, productService.getProductByFootprintCoordinate(latitude,longitude).size());
    }
}