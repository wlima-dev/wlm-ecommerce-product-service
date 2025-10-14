package com.ecommerce.wlm_ecommerce_product_service.domain.repository;

import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.entity.OrderEntity;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    OrderEntity save(OrderEntity order);
    Optional<OrderEntity> findOrderById(Long id);
    List<OrderEntity> findAllOrders(Long userId);
    OrderEntity updateStatus(OrderEntity orderEntity);
    void deleteById(Long id);
}
