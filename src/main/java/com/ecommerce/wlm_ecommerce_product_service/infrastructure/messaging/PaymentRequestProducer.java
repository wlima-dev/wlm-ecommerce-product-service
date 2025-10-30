package com.ecommerce.wlm_ecommerce_product_service.infrastructure.messaging;

import com.ecommerce.wlm_ecommerce_product_service.application.dto.PaymentRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentRequestProducer {
    private final KafkaTemplate<String, PaymentRequestDTO> kafkaTemplate;

    @Value("${kafka.topics.payment-request}")
    private String paymentRequestTopic;

    public void sendPaymentRequest(PaymentRequestDTO request) {
        System.out.println("📤 Sending Payment Request to the topic: " + paymentRequestTopic);
        System.out.println("📦 Data: " + request);
        kafkaTemplate.send(paymentRequestTopic, request.getOrderId().toString(), request);
    }
}
