package com.ecommerce.wlm_ecommerce_product_service.application.dto;

import com.ecommerce.wlm_ecommerce_product_service.domain.model.StatusPayment;

public record UpdatePaymentStatusRequest(StatusPayment statusPayment) {
}
