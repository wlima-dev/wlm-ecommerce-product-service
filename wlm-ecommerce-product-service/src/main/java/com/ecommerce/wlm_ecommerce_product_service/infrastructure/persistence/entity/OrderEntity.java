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
    private Long userId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<OrderItemEntity> items;
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

    public void setOrderItemsEntity(List<OrderItemEntity> items) {
        this.items = items;
        if(items != null){
            for(OrderItemEntity item : items){
                item.setOrder(this);
            }

        }
    }
}
