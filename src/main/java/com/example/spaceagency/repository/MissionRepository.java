package com.example.spaceagency.repository;

import com.example.spaceagency.model.Mission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MissionRepository extends CrudRepository<Mission, Long> {
    List<Mission> findAllByName(String name);
}
