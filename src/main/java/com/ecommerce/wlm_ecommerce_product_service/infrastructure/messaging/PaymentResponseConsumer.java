package com.ecommerce.wlm_ecommerce_product_service.infrastructure.messaging;

import com.ecommerce.wlm_ecommerce_product_service.application.dto.PaymentResponseDTO;
import com.ecommerce.wlm_ecommerce_product_service.application.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentResponseConsumer {
    private final OrderService orderService;

    @KafkaListener(
        topics = "${kafka.topics.payment-response}",
        groupId = "${spring.kafka.consumer.group-id}",
        containerFactory = "kafkaListenerContainerFactory"
    )
    public void consumePaymentResponse(PaymentResponseDTO response) {
        System.out.println("📥 Receiving Payment Response from payment-response-topic");
        System.out.println("✅ Received Data: " + response);
        System.out.println("🎉 Communication among microservices is working!");

        orderService.updateStatus(response.getOrderId(), response.getStatus());
    }
}
