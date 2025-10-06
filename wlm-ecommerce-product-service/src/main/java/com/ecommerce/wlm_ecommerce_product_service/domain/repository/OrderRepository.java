package com.ecommerce.wlm_ecommerce_product_service.domain.repository;

import com.ecommerce.wlm_ecommerce_product_service.domain.model.Order;

import java.util.List;

public interface OrderRepository {
    Order save(Order order);
    Order findOrderById(Long id);
    List<Order> findAllByUserId(Long userId);
    void deleteOrderById(Long id);
}
