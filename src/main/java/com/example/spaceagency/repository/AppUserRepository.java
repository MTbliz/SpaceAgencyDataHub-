package com.example.spaceagency.repository;

import com.example.spaceagency.model.AppUser;
import com.example.spaceagency.model.FileDb;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends CrudRepository<AppUser, Long> {

    @Query(value = "SELECT id, address, email, first_name, last_name FROM app_user where id = (SELECT app_user_id FROM users where id = :securityUserId)"
            , nativeQuery = true)
    Optional<AppUser> findBySecurityUserId(Long securityUserId);
}

