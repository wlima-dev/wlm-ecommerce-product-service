package com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.adapter.mapps;

import com.ecommerce.wlm_ecommerce_product_service.domain.model.OrderItem;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.entity.OrderEntity;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.entity.OrderItemEntity;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.entity.ProductEntity;

public class OrderItemMapper {

    public static OrderItem toDomain(OrderItemEntity entity){
        if(entity == null) return null;

        return new OrderItem(
                entity.getQuantity(),
                entity.getOrder() != null ? entity.getOrder().getId() : null,
                entity.getProductEntity().getId(),
                entity.getProductEntity().getPrice()
        );
    }

    public static OrderItemEntity toEntity(OrderItem domain, OrderEntity orderEntity, ProductEntity productEntity){
        if(domain == null) return null;

        OrderItemEntity entity = new OrderItemEntity();
        entity.setQuantity(domain.getQuantity());
        entity.setOrder(orderEntity);
        entity.setProductEntity(productEntity);
        return entity;
    }
}
