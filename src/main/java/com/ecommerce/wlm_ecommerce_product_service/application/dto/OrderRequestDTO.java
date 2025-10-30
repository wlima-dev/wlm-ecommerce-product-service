package com.ecommerce.wlm_ecommerce_product_service.application.dto;

import com.ecommerce.wlm_ecommerce_product_service.domain.model.OrderItem;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDTO {

    private final Long userId;
    private final List<OrderItem> items;

    public OrderRequestDTO(Long userId, List<OrderItem> items) {
        this.userId = userId;
        this.items = items;
    }

}
