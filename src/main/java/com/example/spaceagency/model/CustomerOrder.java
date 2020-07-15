package com.example.spaceagency.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Set;

@Entity
public class CustomerOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "app_user_id")
    private AppUser customer;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "order_products",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> productList;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private ZonedDateTime orderDate = ZonedDateTime.now();

    public CustomerOrder() {
    }

    public CustomerOrder(AppUser customer, Set<Product> productList, ZonedDateTime orderDate) {
        this.customer = customer;
        this.productList = productList;
        this.orderDate = orderDate;
    }

    public CustomerOrder(Long id, AppUser customer, Set<Product> productList, ZonedDateTime orderDate) {
        this.id = id;
        this.customer = customer;
        this.productList = productList;
        this.orderDate = orderDate;
    }


    public CustomerOrder(Long id, AppUser customer, Set<Product> productList) {
        this.id = id;
        this.customer = customer;
        this.productList = productList;
    }

    public Long getId() {
        return id;
    }

    public Set<Product> getProductList() {
        return productList;
    }

    public AppUser getCustomer() {
        return customer;
    }

    public ZonedDateTime getOrderDate() {
        return orderDate;
    }
}
