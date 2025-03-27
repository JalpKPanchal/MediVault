package com.grownited.repository;

import com.grownited.entity.OtpEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface OtpRepository extends JpaRepository<OtpEntity, UUID> {
    Optional<OtpEntity> findByEmail(String email);

    @Modifying
    @Query("DELETE FROM OtpEntity o WHERE o.email = :email")
    void deleteByEmail(String email);
}