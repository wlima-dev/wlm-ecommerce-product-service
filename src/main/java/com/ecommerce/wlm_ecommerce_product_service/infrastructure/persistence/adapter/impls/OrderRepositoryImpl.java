package com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.adapter.impls;

import com.ecommerce.wlm_ecommerce_product_service.domain.repository.OrderRepository;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.entity.OrderEntity;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.repository.SpringDataOrder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private final SpringDataOrder springDataOrder;

    public OrderRepositoryImpl(SpringDataOrder springDataOrder) {
        this.springDataOrder = springDataOrder;
    }

    @Override
    public OrderEntity save(OrderEntity orderEntity) {
        return springDataOrder.save(orderEntity);
    }

    public Optional<OrderEntity> findOrderById(Long orderId) {
         return springDataOrder.findById(orderId);
    }

    public List<OrderEntity> findAllOrders(Long userId) {
        return springDataOrder.findOrdersByUserId(userId);
    }

    public OrderEntity updateStatus(OrderEntity orderEntity) {
        return springDataOrder.save(orderEntity);
    }

    public void deleteById(Long orderId) {
        springDataOrder.deleteById(orderId);
    }

}
