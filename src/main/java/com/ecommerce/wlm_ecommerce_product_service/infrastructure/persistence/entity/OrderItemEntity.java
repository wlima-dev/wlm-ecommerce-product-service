package com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@NoArgsConstructor
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

    public OrderItemEntity(Long orderItemId, Long productId, int quantity, BigDecimal unitPrice) {
        this.orderItemId = orderItemId;
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

}
