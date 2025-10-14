package com.ecommerce.wlm_ecommerce_product_service.infrastructure.rest.exception;

import com.ecommerce.wlm_ecommerce_product_service.domain.exception.OrderCouldNotDeleteException;
import com.ecommerce.wlm_ecommerce_product_service.domain.exception.ProductNotFoundException;
import com.ecommerce.wlm_ecommerce_product_service.domain.exception.ProductWasNotUpdated;
import com.ecommerce.wlm_ecommerce_product_service.domain.exception.UserNotFoundException;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.rest.dto.OrderErrorResponse;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.rest.dto.ProductErrorResponse;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.rest.dto.UserErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OrderCouldNotDeleteException.class)
    public ResponseEntity<OrderErrorResponse> handleOrderDelete(OrderCouldNotDeleteException e) {
        OrderErrorResponse error = new OrderErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }


    @ExceptionHandler(ProductWasNotUpdated.class)
    public ResponseEntity<ProductErrorResponse> handleProductNotFound(ProductWasNotUpdated e) {
        ProductErrorResponse error = new ProductErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<UserErrorResponse> handleUserNotFound(UserNotFoundException e) {
        UserErrorResponse error = new UserErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ProductErrorResponse> handleProductNotFound(ProductNotFoundException e) {
        ProductErrorResponse error = new ProductErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<UserErrorResponse> handleGenericException(Exception e){
        UserErrorResponse error = new UserErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "An unexpected error occurred",
                LocalDateTime.now()
        );
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
