package com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String statusOrder;
    private String statusPayment;
    private Long userId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<OrderItemsEntity> items;

    public OrderEntity() {}

    public OrderEntity(String statusOrder, String statusPayment, Long userId) {
        this.statusOrder = statusOrder;
        this.statusPayment = statusPayment;
        this.userId = userId;
    }

    public void setOrderItemsEntity(List<OrderItemsEntity> items) {
        this.items = items;
        if(items != null){
            for(OrderItemsEntity item : items){
                item.setOrder(this);
            }

        }
    }
}
