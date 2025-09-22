package com.ecommerce.wlm_ecommerce_product_service.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Order {

    private final String statusOrder;
    private final String statusPayment;
    private final Long userId;
    private final List<OrderItem> items = new ArrayList<>();

    public Order(String statusOrder, String statusPayment, Long userId) {
        this.statusOrder = statusOrder;
        this.statusPayment = statusPayment;
        this.userId = userId;
    }

}
