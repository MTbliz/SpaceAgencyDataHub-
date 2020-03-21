package com.example.spaceagency.model;

import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Set;

@Entity
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToMany()
    @JoinTable(
            name = "order_products",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> productList;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private ZonedDateTime orderDate = ZonedDateTime.now();

    public CustomerOrder() {
    }

    public CustomerOrder(Customer customer, Set<Product> productList, ZonedDateTime orderDate) {
        this.customer=customer;
        this.productList=productList;
        this.orderDate=orderDate;
    }

    public Long getId() {
        return id;
    }

    public Set<Product> getProductList() {
        return productList;
    }

    public Customer getCustomer() {
        return customer;
    }

    public ZonedDateTime getOrderDate() {
        return orderDate;
    }
}
