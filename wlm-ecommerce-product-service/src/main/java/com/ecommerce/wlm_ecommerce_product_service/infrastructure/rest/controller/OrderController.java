package com.ecommerce.wlm_ecommerce_product_service.infrastructure.rest.controller;

import com.ecommerce.wlm_ecommerce_product_service.application.dto.UpdatePaymentStatusRequest;
import com.ecommerce.wlm_ecommerce_product_service.application.service.OrderService;
import com.ecommerce.wlm_ecommerce_product_service.application.service.UserService;
import com.ecommerce.wlm_ecommerce_product_service.domain.model.Order;
import com.ecommerce.wlm_ecommerce_product_service.domain.model.StatusPayment;
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

    public OrderController(OrderService orderService, UserService userService, SpringDataUser springDataUser) {
        this.orderService = orderService;
        this.userService = userService;
        this.springDataUser = springDataUser;
    }

    @PostMapping()
    public ResponseEntity<Order> createOrder(@RequestBody Order order){
        Order savedOrder = orderService.createOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable Long orderId){
        Order order = orderService.getOrderById(orderId);

        if(order == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok().body(order);
    }

    @GetMapping("/all/{userId}")
    public ResponseEntity<List<Order>> getAllUser(@PathVariable Long  userId){
        List<Order> lista = orderService.getAllOrders(userId);
        if(lista == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok().body(lista);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<Order> updateOrder(
            @PathVariable Long orderId,
            @RequestBody UpdatePaymentStatusRequest request) {
        Order order = orderService.changingPaymentStatus(orderId, request.statusPayment());
        return ResponseEntity.ok(order);
    }

    @DeleteMapping("/{userId}/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long userId, @PathVariable Long orderId){
        try {
            orderService.deleteOrder(userId, orderId);
            return ResponseEntity.notFound().build();
        } catch (NoSuchElementException exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
