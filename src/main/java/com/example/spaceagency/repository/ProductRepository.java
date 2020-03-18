package com.example.spaceagency.repository;

import com.example.spaceagency.model.ImageryType;
import com.example.spaceagency.model.Mission;
import com.example.spaceagency.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findByMission(Mission mission);

    List<Product> findByMissionName(String missionName);

    List<Product> findByMission_Type(ImageryType imageryType);

    @Query(value = "from Product p where p.acquisitionDAte BETWEEN :startDate AND :endDate")
    List<Product> findByMissionBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate")LocalDateTime endDate);

    @Query(value = "from Product p where p.acquisitionDAte > :date")
    List<Product> findByMissionGreaterThan(@Param("date") LocalDateTime date);

    @Query(value = "from Product p where p.acquisitionDAte < :date")
    List<Product> findByMissionLessThan(@Param("date") LocalDateTime date);
}
