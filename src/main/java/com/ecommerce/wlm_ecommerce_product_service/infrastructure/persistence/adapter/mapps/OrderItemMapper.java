package com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.adapter.mapps;

import com.ecommerce.wlm_ecommerce_product_service.domain.model.OrderItem;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.entity.OrderItemEntity;

public class OrderItemMapper {

    public static OrderItem toDomain(OrderItemEntity entity) {
        if (entity == null) return null;

        return new OrderItem(
                entity.getQuantity(),
                entity.getOrderItemId(),
                entity.getProductId(),
                entity.getUnitPrice()
        );
    }

    public static OrderItemEntity toEntity(OrderItem orderItem) {
        if (orderItem == null) return null;

        return new OrderItemEntity(
                orderItem.getOrderId(),
                orderItem.getProductId(),
                orderItem.getQuantity(),
                orderItem.getUnitPrice()
        );
    }
}
