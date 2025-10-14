package com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.repository;

import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataOrderItem extends JpaRepository<OrderItemEntity, Long> {
    List<OrderItemEntity> findAllByOrderItemId(Long orderItemId);
    void deleteByOrderItemId(Long orderItemId);
}
