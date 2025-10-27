package com.ecommerce.wlm_ecommerce_product_service.infrastructure.rest.controller;

import com.ecommerce.wlm_ecommerce_product_service.application.dto.OrderRequestDTO;
import com.ecommerce.wlm_ecommerce_product_service.application.dto.OrderResponseDTO;
import com.ecommerce.wlm_ecommerce_product_service.application.dto.UpdatePaymentStatusRequest;
import com.ecommerce.wlm_ecommerce_product_service.application.service.OrderService;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.entity.OrderEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping()
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody OrderRequestDTO request){
        return ResponseEntity.ok(orderService.createOrder(request));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDTO> getOrderWithItems(@PathVariable Long orderId) {
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
        return ResponseEntity.ok(orderService.updateStatus(orderId, request.statusPayment()));
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId){
            orderService.deleteOrder(orderId);
            return ResponseEntity.noContent().build();
    }

}
