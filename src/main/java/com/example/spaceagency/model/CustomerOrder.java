package com.example.spaceagency.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToMany()
    @JoinTable(
            name = "order_products",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> products;

    private Customer customer;

    public Set<Product> getProductList() {
        return products;
    }

    public Customer getCustomer() {
        return customer;
    }
}
