package com.ecommerce.wlm_ecommerce_product_service.application.dto;

import com.ecommerce.wlm_ecommerce_product_service.domain.model.Order;
import com.ecommerce.wlm_ecommerce_product_service.domain.model.OrderItem;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderDTO {
    private String statusOrder;
    private String statusPayment;
    private Long userId;
    private List<OrderItem> orderItems;

    public OrderDTO(String statusOrder, String statusPayment, Long userId, List<OrderItem> orderItems) {
        this.statusOrder = statusOrder;
        this.statusPayment = statusPayment;
        this.userId = userId;
        this.orderItems = orderItems;
    }

    public static Order toDomain(OrderDTO dto) {
        return new Order(
            dto.getStatusOrder(),
            dto.getStatusPayment(),
            dto.getUserId(),
            dto.getOrderItems()
        );
    }

    public static OrderDTO toDTO(Order order) {
        return new OrderDTO(
                order.getStatusOrder(),
                order.getStatusPayment(),
                order.getUserId(),
                order.getItems()
        );
    }

}
