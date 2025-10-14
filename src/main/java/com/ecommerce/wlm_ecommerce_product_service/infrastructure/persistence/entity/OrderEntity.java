package com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.entity;

import com.ecommerce.wlm_ecommerce_product_service.domain.model.StatusOrder;
import com.ecommerce.wlm_ecommerce_product_service.domain.model.StatusPayment;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
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

    public OrderEntity() {}

    public OrderEntity(StatusOrder statusOrder, StatusPayment statusPayment, Long userId, BigDecimal amount) {
        this.statusOrder = statusOrder;
        this.statusPayment = statusPayment;
        this.userId = userId;
        this.amount = amount;
    }

    public OrderEntity(Long id, StatusOrder statusOrder, StatusPayment statusPayment, Long userId, BigDecimal amount) {
        this.id = id;
        this.statusOrder = statusOrder;
        this.statusPayment = statusPayment;
        this.userId = userId;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "id=" + id +
                ", statusOrder=" + statusOrder +
                ", statusPayment=" + statusPayment +
                ", userId=" + userId +
                ", amount=" + amount +
                '}';
    }
}
