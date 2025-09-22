package com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataOrder extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findOrdersByUserId(Long userId);
}
