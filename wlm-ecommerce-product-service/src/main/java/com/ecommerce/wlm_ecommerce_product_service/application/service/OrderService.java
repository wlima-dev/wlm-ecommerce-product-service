package com.ecommerce.wlm_ecommerce_product_service.application.service;

import com.ecommerce.wlm_ecommerce_product_service.domain.model.Order;
import com.ecommerce.wlm_ecommerce_product_service.domain.model.OrderItem;
import com.ecommerce.wlm_ecommerce_product_service.domain.repository.OrderRepository;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.OrderEntity;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.OrderItemsEntity;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.ProductEntity;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.SpringDataProduct;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final SpringDataProduct springDataProduct;

    public OrderService(
            OrderRepository orderRepository,
            SpringDataProduct springDataProduct) {
        this.orderRepository = orderRepository;
        this.springDataProduct = springDataProduct;
    }

    @Transactional
    public OrderEntity createOrder(Order order, Long userId){
        OrderEntity orderEntity = new OrderEntity(
                order.getStatusOrder(),
                order.getStatusPayment(),
                userId
        );

        List<OrderItemsEntity> items = new ArrayList<>();
        for(OrderItem item : order.getItems()){
            ProductEntity product = springDataProduct.findById(item.getProductId())
                    .orElseThrow(() -> new NoSuchElementException("Product not found"));

            OrderItemsEntity itemsEntity = new OrderItemsEntity();
            itemsEntity.setProductEntity(product);
            itemsEntity.setQuantity(item.getQuantity());
            itemsEntity.setOrder(orderEntity); // binding
            items.add(itemsEntity);
        }

        orderEntity.setItems(items);
        return orderRepository.save(orderEntity);
    }

    public OrderEntity getOrderById(Long orderId){
       return orderRepository.findOrderById(orderId);
    }

    public List<OrderEntity> getAllOrders(Long userId){
        return orderRepository.findOrdersByUserId(userId);
    }

    @Transactional
    public void deleteOrderById(Long orderId){
        orderRepository.deleteOrderById(orderId);
    }

}
