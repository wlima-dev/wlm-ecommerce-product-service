package com.ecommerce.wlm_ecommerce_product_service.application.dto;

import com.ecommerce.wlm_ecommerce_product_service.domain.model.Order;
import com.ecommerce.wlm_ecommerce_product_service.domain.model.OrderItem;
import com.ecommerce.wlm_ecommerce_product_service.domain.model.StatusOrder;
import com.ecommerce.wlm_ecommerce_product_service.domain.model.StatusPayment;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
public class OrderDTO {
    private final StatusOrder statusOrder;
    private final StatusPayment statusPayment;
    private final Long userId;
    private final List<OrderItem> orderItems;
    private final BigDecimal amount;

    public OrderDTO(StatusOrder statusOrder, StatusPayment statusPayment, Long userId, List<OrderItem> orderItems, BigDecimal amount) {
        this.statusOrder = statusOrder;
        this.statusPayment = statusPayment;
        this.userId = userId;
        this.orderItems = orderItems;
        this.amount = amount;
    }

    public static Order toDomain(OrderDTO dto) {
        return new Order(
            dto.getUserId(),
            dto.getOrderItems()
        );
    }

    public static OrderDTO toDTO(Order order) {
        return new OrderDTO(
                order.getStatusOrder(),
                order.getStatusPayment(),
                order.getUserId(),
                order.getItems(),
                order.getAmount()
        );
    }

}
