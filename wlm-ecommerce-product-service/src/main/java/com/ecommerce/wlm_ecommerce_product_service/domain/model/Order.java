package com.ecommerce.wlm_ecommerce_product_service.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Order {

    private String statusOrder;
    private String statusPayment;
    private Long userId;
    private List<OrderItem> items;

    public Order(String statusOrder, String statusPayment, Long userId, List<OrderItem> items) {
        this.statusOrder = statusOrder;
        this.statusPayment = statusPayment;
        this.userId = userId;
        this.items = items;
    }

}
