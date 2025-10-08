package com.ecommerce.wlm_ecommerce_product_service.application.service;

import com.ecommerce.wlm_ecommerce_product_service.domain.exception.ProductNotFoundException;
import com.ecommerce.wlm_ecommerce_product_service.domain.exception.UserNotFoundException;
import com.ecommerce.wlm_ecommerce_product_service.domain.model.Order;
import com.ecommerce.wlm_ecommerce_product_service.domain.model.OrderItem;
import com.ecommerce.wlm_ecommerce_product_service.domain.model.StatusPayment;
import com.ecommerce.wlm_ecommerce_product_service.domain.repository.OrderRepository;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.entity.ProductEntity;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.repository.SpringDataProduct;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.repository.SpringDataUser;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final SpringDataProduct springDataProduct;
    private final SpringDataUser springDataUser;

    public OrderService(
            OrderRepository orderRepository,
            SpringDataProduct springDataProduct,
            SpringDataUser springDataUser) {
        this.orderRepository = orderRepository;
        this.springDataProduct = springDataProduct;
        this.springDataUser = springDataUser;
    }

    @Transactional
    public Order createOrder(Order orderRequest){
        if(orderRequest.getUserId() <= 0 || springDataUser.findById(orderRequest.getUserId()).isEmpty()){
            throw new UserNotFoundException(orderRequest.getUserId());
        }

        List<OrderItem> enrichedItems = orderRequest.getItems().stream()
                .map(item -> {
                    ProductEntity product = springDataProduct.findById(item.getProductId())
                            .orElseThrow(() -> new ProductNotFoundException("Product not found: " + item.getProductId()));

                    return new OrderItem(
                            item.getQuantity(),
                            null, // orderId not exists yet
                            item.getProductId(),
                            product.getPrice()
                    );
                }).collect(Collectors.toList());

        return orderRepository.save(new Order(orderRequest.getUserId(), enrichedItems));
    }

    public Order getOrderById(Long orderId){
       if(orderId <= 0){
           throw new IllegalArgumentException("orderId must be greater than zero");
       }

        return orderRepository.findOrderById(orderId);
    }

    public List<Order> getAllOrders(Long userId){
        if(userId <= 0){
            throw new IllegalArgumentException("userId must be greater than zero");
        }
        return orderRepository.findAllByUserId(userId);
    }

    public Order changingPaymentStatus(Long orderId, StatusPayment statusPayment){
        Order order = orderRepository.findOrderById(orderId);
        order.changeStatusPayment(statusPayment);
        return orderRepository.save(order);
    }

    @Transactional
    public void deleteOrder(Long userId, Long orderId){
        if(orderId <= 0){
            throw new IllegalArgumentException("Order id must to be greater than 0");
        }

        springDataUser.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        orderRepository.deleteOrderById(orderId);
    }

}
