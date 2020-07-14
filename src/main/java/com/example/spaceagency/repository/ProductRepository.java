package com.example.spaceagency.repository;

import com.example.spaceagency.model.*;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findByMission(Mission mission);

    List<Product> findByMissionName(String missionName);

    List<Product> findByMission_Type(ImageryType imageryType);

    Optional<Product> findByUrl(String url);

    @Query(value = "from Product p where p.acquisitionDate BETWEEN :startDate AND :endDate")
    List<Product> findByMissionBetween(@Param("startDate") ZonedDateTime startDate, @Param("endDate") ZonedDateTime endDate);

    @Query(value = "from Product p where p.acquisitionDate > :date")
    List<Product> findByMissionGreaterThan(@Param("date") ZonedDateTime date);

    @Query(value = "from Product p where p.acquisitionDate < :date")
    List<Product> findByMissionLessThan(@Param("date") ZonedDateTime date);

    @Query(value = "SELECT product_id, acquisition_date, price, url, footprint_id, mission_id, file_db_id FROM product " +
            "WHERE footprint_id IN (SELECT COORDINATES_footprint_id FROM COORDINATE " +
            "WHERE LATITUDE = :latitude AND LONGITUDE = :longitude )"
            , nativeQuery = true)
    List<Product> findByFootprint_Coordinates(@Param("latitude") double latitude, @Param("longitude") double longitude);

    @Query(value = "SELECT product_id, acquisition_date, price, url, footprint_id, mission_id, file_db_id FROM product " +
            "WHERE product_id " +
            "IN( SELECT product_id FROM order_products GROUP BY product_id HAVING COUNT(product_id) = " +
            "(SELECT COUNT(product_id) count FROM order_products GROUP BY product_id ORDER BY count DESC LIMIT 1));"
            , nativeQuery = true)
    List<Product> findMostOrdered();

    @Modifying
    @Query( "update Product p set p.url = :url where p.id = :id")
    void updateProductURL(@Param("id") Long id, @Param("url") String url);


    @Query( value = "SELECT product_id, acquisition_date, price, url, null as file_db_id, footprint_id, mission_id FROM product", nativeQuery = true)
    List<Product> findAllProductsWithoutFile();
}
