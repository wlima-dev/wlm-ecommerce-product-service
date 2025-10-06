package com.ecommerce.wlm_ecommerce_product_service.domain.model;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class OrderItem {
    private int quantity;
    private Long orderId;
    private Long productId;
    private BigDecimal unitPrice;

    public OrderItem() {}

    public OrderItem(int quantity, Long orderId, Long productId, BigDecimal unitPrice) {
        this.quantity = quantity;
        this.orderId = orderId;
        this.productId = productId;
        this.unitPrice = unitPrice;
    }

    public BigDecimal getSubtotal() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "quantity=" + quantity +
                ", orderId=" + orderId +
                ", productId=" + productId +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
