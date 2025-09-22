package com.ecommerce.wlm_ecommerce_product_service.domain.repository;

import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.OrderEntity;

import java.util.List;

public interface OrderRepository {
    OrderEntity save(OrderEntity orderEntity);
    OrderEntity findOrderById(Long id);
    List<OrderEntity> findOrdersByUserId(Long userId);
    void deleteOrderById(Long id);
}
