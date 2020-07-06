package com.example.spaceagency.repository;

import com.example.spaceagency.model.FileDb;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileDbRepository extends CrudRepository<FileDb,Long> {

    @Query(value = "SELECT id, data, file_name, file_type FROM filedb where id = (SELECT file_db_id FROM product where url = :urlString)"
            , nativeQuery = true)
    Optional<FileDb> findByUrl(String urlString);

}

