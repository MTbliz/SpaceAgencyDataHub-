package com.example.spaceagency.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ConfigurationRepository {

    private final CustomerRepository customerRepository;

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ConfigurationRepository(CustomerRepository customerRepository, JdbcTemplate jdbcTemplate) {
        this.customerRepository = customerRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public String insertInitialData() {
        if (!customerRepository.findAll().iterator().hasNext()) {
            jdbcTemplate.update("INSERT INTO customer (customer_id, first_name, last_name) VALUES (1, 'Jan', 'Kowalski');");
            return "Records added to database";
        } else {
            return "You can't add records to database. Records already added";
        }
    }
}
