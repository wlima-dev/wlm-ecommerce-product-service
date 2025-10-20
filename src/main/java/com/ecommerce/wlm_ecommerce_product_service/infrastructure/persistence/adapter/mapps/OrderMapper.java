package com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.adapter.mapps;

import com.ecommerce.wlm_ecommerce_product_service.domain.model.Order;
import com.ecommerce.wlm_ecommerce_product_service.domain.model.OrderItem;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.entity.OrderEntity;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.entity.OrderItemEntity;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.repository.SpringDataOrderItem;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class OrderMapper {

    private static SpringDataOrderItem springDataOrderItem;

    public OrderMapper(SpringDataOrderItem springDataOrderItem) {
        this.springDataOrderItem = springDataOrderItem;
    }

    public static OrderEntity toEntity(Order order){
        return new OrderEntity(
                order.getStatusOrder(),
                order.getStatusPayment(),
                order.getUserId(),
                order.getAmount()
        );
    }

    public static Order toDomain(OrderEntity entity){
        List<OrderItemEntity> itemEntities = springDataOrderItem.findAllByOrderItemId(entity.getId());

        List<OrderItem> items = new ArrayList<>();
        for (OrderItemEntity item : itemEntities) {
            items.add(OrderItemMapper.toDomain(item));
        }

        return new Order(
                entity.getId(),
                entity.getUserId(),
                items
        );
    }
}
