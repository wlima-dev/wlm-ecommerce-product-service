package com.ecommerce.wlm_ecommerce_product_service.domain.model;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
public class Order {

    private Long id;
    private StatusOrder statusOrder;
    private StatusPayment statusPayment;
    private Long userId;
    private List<OrderItem> items;
    private BigDecimal amount;

    public Order() {}

    public Order(Long userId, List<OrderItem> items) {
        this.statusOrder = StatusOrder.CREATED;
        this.statusPayment = StatusPayment.PENDING;
        this.userId = userId;
        this.items = items;
        this.amount = calculateTotalAmount();
    }

    public Order(Long id, Long userId, List<OrderItem> items) {
        this.id = id;
        this.statusOrder = StatusOrder.CREATED;
        this.statusPayment = StatusPayment.PENDING;
        this.userId = userId;
        this.items = items;
        this.amount = calculateTotalAmount();
    }

    public void changeStatusPayment(StatusPayment statusPayment) {
        this.statusPayment = statusPayment;

        if (statusPayment == StatusPayment.REFUSED) {
            this.statusOrder = StatusOrder.CANCELLED;
        }

        if (statusPayment == StatusPayment.SUCCESS && this.statusOrder == StatusOrder.CREATED) {
            this.statusOrder = StatusOrder.CONFIRMED;
        }
    }

    private BigDecimal calculateTotalAmount() {
        if (items == null || items.isEmpty()) {
            return BigDecimal.ZERO;
        }

        return items.stream()
                .map(OrderItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
