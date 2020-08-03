package com.fsoft.filesharingbackend.repository;

import com.fsoft.filesharingbackend.entity.Files;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<Files, Long> {

    Boolean existsByMd5(String s);

    Optional<Files> findByMd5(String md5);
}