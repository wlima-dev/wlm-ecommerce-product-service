package com.ecommerce.wlm_ecommerce_product_service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    private Long id;
    private int quantity;
    private Long orderId;
    private Long productId;
    private BigDecimal unitPrice;

    public OrderItem(int quantity, Long orderId, Long productId, BigDecimal unitPrice) {
        this.quantity = quantity;
        this.orderId = orderId;
        this.productId = productId;
        this.unitPrice = unitPrice;
    }

    public BigDecimal getSubtotal() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }

}
