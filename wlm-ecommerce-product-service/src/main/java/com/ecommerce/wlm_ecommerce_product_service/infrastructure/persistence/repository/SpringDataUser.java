package com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.repository;

import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataUser extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
}
