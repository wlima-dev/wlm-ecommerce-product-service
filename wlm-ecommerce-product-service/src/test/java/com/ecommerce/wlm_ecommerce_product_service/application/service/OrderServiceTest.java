package com.ecommerce.wlm_ecommerce_product_service.application.service;

import com.ecommerce.wlm_ecommerce_product_service.domain.exception.UserNotFoundException;
import com.ecommerce.wlm_ecommerce_product_service.domain.model.Order;
import com.ecommerce.wlm_ecommerce_product_service.domain.model.OrderItem;
import com.ecommerce.wlm_ecommerce_product_service.domain.model.StatusOrder;
import com.ecommerce.wlm_ecommerce_product_service.domain.model.StatusPayment;
import com.ecommerce.wlm_ecommerce_product_service.domain.repository.OrderRepository;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.entity.ProductEntity;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.entity.UserEntity;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.repository.SpringDataProduct;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.repository.SpringDataUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;

    @Mock
    private SpringDataProduct springDataProduct;

    @Mock
    private SpringDataUser springDataUser;

    @InjectMocks
    private OrderService orderService;

    private ProductEntity mockProduct;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        mockProduct = new ProductEntity();
        mockProduct.setId(1L);
        mockProduct.setPrice(BigDecimal.valueOf(100));
    }

    @Test
    @DisplayName("should create Order if products and user exists")
    void shouldCreateOrderSuccessfully() {
        // arrange
        OrderItem item = new OrderItem(2, null, 1L, new BigDecimal(BigInteger.valueOf(100L)));
        Order orderRequest = new Order(10L, List.of(item));

        when(springDataUser.findById(10L)).thenReturn(Optional.of(new UserEntity()));
        when(springDataProduct.findById(1L)).thenReturn(Optional.of(mockProduct));
        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // act
        Order savedOrder = orderService.createOrder(orderRequest);

        // assert
        assertNotNull(savedOrder);
        assertEquals(BigDecimal.valueOf(200), savedOrder.getAmount());
        assertEquals(StatusOrder.CREATED, savedOrder.getStatusOrder());
        assertEquals(StatusPayment.PENDING, savedOrder.getStatusPayment());
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    @DisplayName("Should return Order if id exists")
    void shouldGetOrderById() {
        Order expectedOrder = new Order(1L, 10L, List.of());
        when(orderRepository.findOrderById(1L)).thenReturn(expectedOrder);

        Order found = orderService.getOrderById(1L);

        assertEquals(expectedOrder, found);
        verify(orderRepository, times(1)).findOrderById(1L);
    }

    @Test
    @DisplayName("Should throw exception if ID is invalid")
    void shouldThrowWhenInvalidOrderId() {
        assertThrows(IllegalArgumentException.class, () -> orderService.getOrderById(0L));
        verifyNoInteractions(orderRepository);
    }

    @Test
    @DisplayName("Should update status payment to SUCCESS and confirm the Order")
    void shouldChangePaymentStatusToSuccess() {
        Order order = new Order(1L, 10L, List.of());
        when(orderRepository.findOrderById(1L)).thenReturn(order);
        when(orderRepository.save(any(Order.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Order updated = orderService.changingPaymentStatus(1L, StatusPayment.SUCCESS);

        assertEquals(StatusPayment.SUCCESS, updated.getStatusPayment());
        assertEquals(StatusOrder.CONFIRMED, updated.getStatusOrder());
    }

    @Test
    @DisplayName("Should delete ORDER if User exists")
    void shouldDeleteOrderSuccessfully() {
        when(springDataUser.findById(10L)).thenReturn(Optional.of(new UserEntity()));

        orderService.deleteOrder(10L, 5L);

        verify(orderRepository, times(1)).deleteOrderById(5L);
    }

    @Test
    @DisplayName("Should throw UserNotFoundException if userId does not exists when order delete method is called.")
    void shouldThrowWhenDeletingWithUserNotFound() {
        when(springDataUser.findById(99L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class,
                () -> orderService.deleteOrder(99L, 5L));

        verify(orderRepository, never()).deleteOrderById(anyLong());
    }

}