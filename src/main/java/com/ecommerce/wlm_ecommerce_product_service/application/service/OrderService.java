package com.ecommerce.wlm_ecommerce_product_service.application.service;

import com.ecommerce.wlm_ecommerce_product_service.application.dto.OrderResponse;
import com.ecommerce.wlm_ecommerce_product_service.domain.exception.OrderCouldNotDeleteException;
import com.ecommerce.wlm_ecommerce_product_service.domain.exception.ProductWasNotUpdated;
import com.ecommerce.wlm_ecommerce_product_service.domain.exception.UserNotFoundException;
import com.ecommerce.wlm_ecommerce_product_service.domain.model.Order;
import com.ecommerce.wlm_ecommerce_product_service.domain.model.OrderItem;
import com.ecommerce.wlm_ecommerce_product_service.domain.model.StatusOrder;
import com.ecommerce.wlm_ecommerce_product_service.domain.model.StatusPayment;
import com.ecommerce.wlm_ecommerce_product_service.domain.repository.OrderRepository;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.adapter.mapps.OrderMapper;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.entity.OrderEntity;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.entity.OrderItemEntity;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.repository.SpringDataOrderItem;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.repository.SpringDataProduct;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.repository.SpringDataUser;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final SpringDataProduct springDataProduct;
    private final SpringDataUser springDataUser;
    private final SpringDataOrderItem springDataOrderItem;

    public OrderService(
            OrderRepository orderRepository,
            SpringDataProduct springDataProduct,
            SpringDataUser springDataUser,
            SpringDataOrderItem springDataOrderItem) {
        this.orderRepository = orderRepository;
        this.springDataProduct = springDataProduct;
        this.springDataUser = springDataUser;
        this.springDataOrderItem = springDataOrderItem;
    }

    @Transactional
    public void createOrder(Order orderRequest){

        BigDecimal totalAmount = orderRequest.getItems().stream()
                .map(i -> i.getUnitPrice().multiply(BigDecimal.valueOf(i.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        OrderEntity orderEntity = new OrderEntity(
                StatusOrder.CREATED,
                StatusPayment.PENDING,
                orderRequest.getUserId(),
                totalAmount
        );

        OrderEntity entity = orderRepository.save(orderEntity);
        if(entity == null){throw new NoSuchElementException("The order could not be created");}
        for(OrderItem item : orderRequest.getItems()){
            OrderItemEntity itemEntity = new OrderItemEntity(
                    entity.getId(),
                    item.getProductId(),
                    item.getQuantity(),
                    item.getUnitPrice()
            );
            springDataOrderItem.save(itemEntity);
        }
    }

    public OrderResponse getOrderById(@Positive Long orderId){
        OrderEntity order = orderRepository.findOrderById(orderId)
                .orElseThrow(() -> new NoSuchElementException("Order not found with id: " + orderId));

        List<OrderItemEntity> items = springDataOrderItem.findAllByOrderItemId(orderId);

        return new OrderResponse(
                order.getId(),
                order.getStatusOrder().name(),
                order.getStatusPayment().name(),
                order.getUserId(),
                order.getAmount(),
                items
        );
    }

    public List<OrderEntity> getAllOrders(Long userId){
        if(userId <= 0){
            throw new IllegalArgumentException("The id must be valid!");
        }

        return orderRepository.findAllOrders(userId);
    }

    public OrderEntity updateStatus(Long orderId, StatusPayment statusPayment){
        OrderEntity orderEntity = orderRepository.findOrderById(orderId)
                .orElseThrow(() -> new NoSuchElementException("Order not found with id: " + orderId));

        orderEntity.setStatusPayment(statusPayment);
        return orderRepository.save(orderEntity);
    }

    @Transactional
    public void deleteOrder(Long orderId){
        if(orderId <= 0){
            throw new IllegalArgumentException("Order id must to be greater than 0");
        }

        try {
            springDataOrderItem.deleteByOrderItemId(orderId);
            orderRepository.deleteById(orderId);
        } catch (OrderCouldNotDeleteException e){
            throw new OrderCouldNotDeleteException("Order could not be deleted");
        }
    }

}
