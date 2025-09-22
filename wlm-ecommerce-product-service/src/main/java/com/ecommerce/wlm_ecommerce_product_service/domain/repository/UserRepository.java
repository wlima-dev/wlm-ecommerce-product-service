package com.ecommerce.wlm_ecommerce_product_service.domain.repository;

import com.ecommerce.wlm_ecommerce_product_service.domain.model.User;

import java.util.List;

public interface UserRepository {
    User save(User user);
    User findByEmail(String email);
    List<User> findAllUsers();
    void delete(Long id);
    User findById(Long id);
    User updateUser(User user, Long id);
}
