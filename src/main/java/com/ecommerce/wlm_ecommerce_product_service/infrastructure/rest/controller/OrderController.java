package com.ecommerce.wlm_ecommerce_product_service.infrastructure.rest.controller;

import com.ecommerce.wlm_ecommerce_product_service.application.dto.OrderResponse;
import com.ecommerce.wlm_ecommerce_product_service.application.dto.UpdatePaymentStatusRequest;
import com.ecommerce.wlm_ecommerce_product_service.application.service.OrderService;
import com.ecommerce.wlm_ecommerce_product_service.application.service.UserService;
import com.ecommerce.wlm_ecommerce_product_service.domain.model.Order;
import com.ecommerce.wlm_ecommerce_product_service.domain.model.OrderItem;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.entity.OrderEntity;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.entity.OrderItemEntity;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.repository.SpringDataOrderItem;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.repository.SpringDataUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/order")
public class OrderController {

    OrderService orderService;
    UserService userService;
    SpringDataUser springDataUser;
    SpringDataOrderItem springDataOrderItem;

    public OrderController(
            OrderService orderService,
            UserService userService,
            SpringDataUser springDataUser,
            SpringDataOrderItem springDataOrderItem) {
        this.orderService = orderService;
        this.userService = userService;
        this.springDataUser = springDataUser;
        this.springDataOrderItem = springDataOrderItem;
    }

    @PostMapping()
    public ResponseEntity<Void> createOrder(@RequestBody Order order){
        orderService.createOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrderWithItems(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

    @GetMapping("/all/{userId}")
    public ResponseEntity<List<OrderEntity>> getAllUser(@PathVariable Long  userId){
        return ResponseEntity.ok().body(orderService.getAllOrders(userId));
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderEntity> updateOrder(
            @PathVariable Long orderId,
            @RequestBody UpdatePaymentStatusRequest request) {
        OrderEntity orderEntity = orderService.updateStatus(orderId, request.statusPayment());
        return ResponseEntity.ok(orderEntity);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId){
            orderService.deleteOrder(orderId);
            return ResponseEntity.noContent().build();
    }

}
