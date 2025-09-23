package com.ecommerce.wlm_ecommerce_product_service.application.dto;

import com.ecommerce.wlm_ecommerce_product_service.domain.model.Order;
import com.ecommerce.wlm_ecommerce_product_service.domain.model.OrderItem;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class OrderDTO {
    private final String statusOrder;
    private final String statusPayment;
    private final Long userId;

    public OrderDTO(String statusOrder, String statusPayment, Long userId) {
        this.statusOrder = statusOrder;
        this.statusPayment = statusPayment;
        this.userId = userId;
    }

    public static Order toDomain(OrderDTO dto) {
        return new Order(
            dto.getStatusOrder(),
                dto.getStatusPayment(),
                dto.getUserId()
        );
    }

    public static OrderDTO toDTO(Order order) {
        return new OrderDTO(
                order.getStatusOrder(),
                order.getStatusPayment(),
                order.getUserId()
        );
    }

}
