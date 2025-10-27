package com.ecommerce.wlm_ecommerce_product_service.application.service;

import com.ecommerce.wlm_ecommerce_product_service.application.dto.PaymentRequestDTO;
import com.ecommerce.wlm_ecommerce_product_service.domain.exception.OrderAlreadyPaidException;
import com.ecommerce.wlm_ecommerce_product_service.domain.exception.OrderNotFoundException;
import com.ecommerce.wlm_ecommerce_product_service.domain.model.StatusPayment;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.messaging.PaymentRequestProducer;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.entity.OrderEntity;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.repository.SpringDataOrder;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRequestProducer paymentRequestProducer;
    private final SpringDataOrder springDataOrder;

    public void doPayment(@Positive Long orderId) throws OrderNotFoundException, OrderAlreadyPaidException {
        OrderEntity order = springDataOrder.findById(orderId).orElseThrow(
                () -> new OrderNotFoundException("Could not found order for this id"));

        if(order.getStatusPayment().equals(StatusPayment.SUCCESS)){
            throw new OrderAlreadyPaidException("This order is already paid.");
        }

        PaymentRequestDTO request = new PaymentRequestDTO(
                order.getId(),
                order.getUserId(),
                order.getAmount(),
                order.getStatusPayment()
        );

        paymentRequestProducer.sendPaymentRequest(request);
    }
}
