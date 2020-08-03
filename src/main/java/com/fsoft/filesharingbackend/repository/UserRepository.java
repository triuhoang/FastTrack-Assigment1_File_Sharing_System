package com.fsoft.filesharingbackend.repository;

import com.fsoft.filesharingbackend.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByUsernameAndPassword(String username, String password);
}