package com.ecommerce.wlm_ecommerce_product_service.domain.repository;

import com.ecommerce.wlm_ecommerce_product_service.domain.model.User;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.entity.UserEntity;

import java.util.List;

public interface UserRepository {
    UserEntity save(UserEntity user);
    List<User> findAll();
    void delete(Long id);
    UserEntity findById(Long id);
}
