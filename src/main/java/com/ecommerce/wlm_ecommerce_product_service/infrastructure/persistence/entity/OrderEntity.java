package com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.entity;

import com.ecommerce.wlm_ecommerce_product_service.domain.model.StatusOrder;
import com.ecommerce.wlm_ecommerce_product_service.domain.model.StatusPayment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private StatusOrder statusOrder;

    @Enumerated(EnumType.STRING)
    private StatusPayment statusPayment;

    @Column(name = "user_id")
    private Long userId;

    private BigDecimal amount;

    public OrderEntity(StatusOrder statusOrder, StatusPayment statusPayment, Long userId, BigDecimal amount) {
        this.statusOrder = statusOrder;
        this.statusPayment = statusPayment;
        this.userId = userId;
        this.amount = amount;
    }

}
