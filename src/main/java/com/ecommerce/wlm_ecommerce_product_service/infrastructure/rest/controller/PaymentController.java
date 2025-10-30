package com.ecommerce.wlm_ecommerce_product_service.infrastructure.rest.controller;

import com.ecommerce.wlm_ecommerce_product_service.application.service.PaymentService;
import com.ecommerce.wlm_ecommerce_product_service.domain.exception.OrderAlreadyPaidException;
import com.ecommerce.wlm_ecommerce_product_service.domain.exception.OrderNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/{orderId}")
    public void sendMessage(@PathVariable Long orderId) throws OrderNotFoundException, OrderAlreadyPaidException {
        paymentService.doPayment(orderId);
    }

}
