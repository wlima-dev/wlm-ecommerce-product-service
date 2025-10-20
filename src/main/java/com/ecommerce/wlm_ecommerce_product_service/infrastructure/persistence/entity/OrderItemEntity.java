package com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Entity
@Table(name = "order_items")
public class OrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    @Column(name = "order_id")
    private Long orderItemId;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    public OrderItemEntity() {}

    public OrderItemEntity(Long orderItemId, Long productId, int quantity, BigDecimal unitPrice) {
        this.orderItemId = orderItemId;
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "OrderItemEntity{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", orderItemId=" + orderItemId +
                ", productId=" + productId +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
