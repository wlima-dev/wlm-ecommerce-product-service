package com.ecommerce.wlm_ecommerce_product_service.application.dto;

import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.entity.OrderItemEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderResponse {
    private Long id;
    private String statusOrder;
    private String statusPayment;
    private Long userId;
    private BigDecimal amount;
    private List<OrderItemEntity> items;

    public OrderResponse(Long id, String statusOrder, String statusPayment, Long userId, BigDecimal amount, List<OrderItemEntity> items) {
        this.id = id;
        this.statusOrder = statusOrder;
        this.statusPayment = statusPayment;
        this.userId = userId;
        this.amount = amount;
        this.items = items;
    }

}
