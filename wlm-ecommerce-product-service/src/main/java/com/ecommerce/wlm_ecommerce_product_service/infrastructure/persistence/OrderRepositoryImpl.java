package com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence;

import com.ecommerce.wlm_ecommerce_product_service.domain.repository.OrderRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    public SpringDataOrder springDataOrder;

    public OrderRepositoryImpl(SpringDataOrder springDataOrder) {
        this.springDataOrder = springDataOrder;
    }

    @Override
    public OrderEntity save(OrderEntity orderEntity) {
        return springDataOrder.save(orderEntity);
    }

    public OrderEntity findOrderById(Long orderId) {
        return springDataOrder.findById(orderId)
                .orElseThrow(() -> new NoSuchElementException("Order: ["+orderId+"] was not Found"));
    }

    public List<OrderEntity> findOrdersByUserId(Long userId) {
        return springDataOrder.findOrdersByUserId(userId);

    }

    public void deleteOrderById(Long orderId) {
        springDataOrder.deleteById(orderId);
    }

}
