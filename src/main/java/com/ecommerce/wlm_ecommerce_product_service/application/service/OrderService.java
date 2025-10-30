package com.ecommerce.wlm_ecommerce_product_service.application.service;

import com.ecommerce.wlm_ecommerce_product_service.application.dto.OrderRequestDTO;
import com.ecommerce.wlm_ecommerce_product_service.application.dto.OrderResponseDTO;
import com.ecommerce.wlm_ecommerce_product_service.domain.exception.OrderCouldNotDeleteException;
import com.ecommerce.wlm_ecommerce_product_service.domain.exception.OrderNotFoundException;
import com.ecommerce.wlm_ecommerce_product_service.domain.model.Order;
import com.ecommerce.wlm_ecommerce_product_service.domain.model.OrderItem;
import com.ecommerce.wlm_ecommerce_product_service.domain.model.StatusPayment;
import com.ecommerce.wlm_ecommerce_product_service.domain.repository.OrderRepository;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.entity.OrderEntity;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.entity.OrderItemEntity;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.repository.SpringDataOrderItem;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final SpringDataOrderItem springDataOrderItem;

    @Transactional
    public OrderResponseDTO createOrder(OrderRequestDTO orderRequest){

        Order order = new Order(
                orderRequest.getUserId(),
                orderRequest.getItems()
        );

        OrderEntity orderEntity = new OrderEntity(
            order.getStatusOrder(),
            order.getStatusPayment(),
            order.getUserId(),
            order.getAmount()
        );

        OrderEntity savedEntity = orderRepository.save(orderEntity);

        List<OrderItemEntity> fullItemsList = new ArrayList<>();
        for(OrderItem item : orderRequest.getItems()){
            OrderItemEntity itemEntity = new OrderItemEntity(
                    savedEntity.getId(),
                    item.getProductId(),
                    item.getQuantity(),
                    item.getUnitPrice()
            );
            fullItemsList.add(springDataOrderItem.save(itemEntity));
        }

        return new OrderResponseDTO(
                orderEntity.getId(),
                orderEntity.getStatusOrder(),
                orderEntity.getStatusPayment(),
                orderEntity.getUserId(),
                orderEntity.getAmount(),
                fullItemsList
        );
    }

    public OrderResponseDTO getOrderById(@Positive Long orderId) {
        try {
            OrderEntity order = orderRepository.findOrderById(orderId)
                    .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + orderId));

            List<OrderItemEntity> items = springDataOrderItem.findAllByOrderItemId(orderId);

            return new OrderResponseDTO(
                    order.getId(),
                    order.getStatusOrder(),
                    order.getStatusPayment(),
                    order.getUserId(),
                    order.getAmount(),
                    items
            );
        } catch (OrderNotFoundException e) {
            throw new RuntimeException("Failed to fetch order: " + e.getMessage(), e);
        }
    }

    public List<OrderEntity> getAllOrders(@Positive Long userId){
        return orderRepository.findAllOrders(userId);
    }

    public OrderEntity updateStatus(Long orderId, StatusPayment statusPayment){
        OrderEntity orderEntity = orderRepository.findOrderById(orderId)
                .orElseThrow(() -> new NoSuchElementException("Order not found with id: " + orderId));

        orderEntity.setStatusPayment(statusPayment);
        return orderRepository.save(orderEntity);
    }

    @Transactional
    public void deleteOrder(@Positive Long orderId){
        try {
            springDataOrderItem.deleteByOrderItemId(orderId);
            orderRepository.deleteById(orderId);
        } catch (Exception e) {
            OrderCouldNotDeleteException domainException =
                new OrderCouldNotDeleteException("Could not delete order with ID: " + orderId);

            throw new RuntimeException(domainException);
        }
    }

}
