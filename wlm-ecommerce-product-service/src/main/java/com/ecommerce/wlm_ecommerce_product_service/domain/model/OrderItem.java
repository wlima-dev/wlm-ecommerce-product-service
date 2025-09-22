package com.ecommerce.wlm_ecommerce_product_service.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItem {

    private final Long productId;
    private final int quantity;
    private final Long orderId;

    public OrderItem(Long productId, int quantity, Long orderId) {
        this.productId = productId;
        this.quantity = quantity;
        this.orderId = orderId;
    }

}
