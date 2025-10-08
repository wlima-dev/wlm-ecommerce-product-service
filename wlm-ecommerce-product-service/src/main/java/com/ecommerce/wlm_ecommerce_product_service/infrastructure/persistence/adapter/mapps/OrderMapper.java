package com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.adapter.mapps;

import com.ecommerce.wlm_ecommerce_product_service.domain.model.Order;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.entity.OrderEntity;

public class OrderMapper {

    public static OrderEntity toEntity(Order order){
        return new OrderEntity(
                order.getId(),
                order.getStatusOrder(),
                order.getStatusPayment(),
                order.getUserId(),
                order.getAmount()
        );
    }

    public static Order toDomain(OrderEntity orderEntity){
        return new Order(
                orderEntity.getId(),
                orderEntity.getUserId(),
                orderEntity.getItems()
                        .stream()
                        .map(OrderItemMapper::toDomain)
                        .toList()
        );
    }
}
