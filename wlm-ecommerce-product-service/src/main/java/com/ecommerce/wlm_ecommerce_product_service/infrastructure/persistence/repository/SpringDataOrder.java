package com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.repository;

import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataOrder extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findOrdersByUserId(Long userId);
}
