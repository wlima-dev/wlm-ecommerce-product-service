package com.ecommerce.wlm_ecommerce_product_service.infrastructure.rest.controller;

import com.ecommerce.wlm_ecommerce_product_service.application.service.OrderService;
import com.ecommerce.wlm_ecommerce_product_service.application.service.UserService;
import com.ecommerce.wlm_ecommerce_product_service.domain.model.Order;
import com.ecommerce.wlm_ecommerce_product_service.domain.model.User;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.entity.OrderEntity;
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
    public ResponseEntity<OrderEntity> createOrder(@RequestBody Order order){
        if(order.getUserId() <= 0 || springDataUser.findById(order.getUserId()).isEmpty()){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        OrderEntity savedOrder = orderService.createOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
    }

    @GetMapping("/{userId}/{orderId}")
    public ResponseEntity<OrderEntity> getOrder(@PathVariable Long userId, @PathVariable Long orderId){
        User user = userService.findById(userId);
        if(user == null){
           throw new NoSuchElementException("No element was found");
        }

        OrderEntity orderEntity = orderService.getOrderById(orderId);
        if(orderEntity == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok().body(orderEntity);
    }

    @GetMapping("/all/{userId}")
    public ResponseEntity<List<OrderEntity>> getAllUser(@PathVariable Long  userId){
        springDataUser.findById(userId)
            .orElseThrow(() -> new NoSuchElementException("User not found"));

        List<OrderEntity> lista = orderService.getAllOrders(userId);
        if(lista == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok().body(lista);
    }

    @DeleteMapping("/{userId}/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long userId, @PathVariable Long orderId){
        User user = userService.findById(userId);
        if(user == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        orderService.deleteOrderById(orderId);
        return ResponseEntity.ok().build();
    }
}
