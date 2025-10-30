package com.ecommerce.wlm_ecommerce_product_service.application.service;

import com.ecommerce.wlm_ecommerce_product_service.application.dto.OrderRequestDTO;
import com.ecommerce.wlm_ecommerce_product_service.application.dto.OrderResponseDTO;
import com.ecommerce.wlm_ecommerce_product_service.domain.exception.OrderCouldNotDeleteException;
import com.ecommerce.wlm_ecommerce_product_service.domain.model.*;
import com.ecommerce.wlm_ecommerce_product_service.domain.repository.OrderRepository;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.entity.OrderEntity;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.entity.OrderItemEntity;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.entity.ProductEntity;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.repository.SpringDataOrderItem;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.repository.SpringDataProduct;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.repository.SpringDataUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
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

    @Mock
    private SpringDataOrderItem springDataOrderItem;

    private ProductEntity mockProduct;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        mockProduct = new ProductEntity();
        mockProduct.setId(1L);
        mockProduct.setPrice(BigDecimal.valueOf(100));
    }

    @Test
    @DisplayName("should create Order successfully")
    void shouldCreateOrderSuccessfully() {
        // Arrange
        OrderEntity entity = new OrderEntity(2L,StatusOrder.CREATED,StatusPayment.SUCCESS,5L,new BigDecimal(600));
        OrderItemEntity itemEntity = new OrderItemEntity(2L,13L,2,new BigDecimal(300));
        OrderItem item = new OrderItem(1, 2L, 13L, new BigDecimal(150));
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(item);
        OrderRequestDTO order = new OrderRequestDTO(1L, orderItems);

        when(orderRepository.save(any(OrderEntity.class))).thenReturn(entity);
        when(springDataOrderItem.save(any(OrderItemEntity.class))).thenReturn(itemEntity);

        // Act
        orderService.createOrder(order);

        // Assert
        verify(orderRepository, times(1)).save(any(OrderEntity.class));
        verify(springDataOrderItem, times(1)).save(any(OrderItemEntity.class));

    }

    @Test
    @DisplayName("Should return OrderResponse when order exists")
    void shouldReturnOrderWhenExists() {
        // Arrange
        OrderEntity entity = new OrderEntity(2L, StatusOrder.CREATED, StatusPayment.SUCCESS, 5L, new BigDecimal("600.00"));
        when(orderRepository.findOrderById(2L)).thenReturn(Optional.of(entity));

        // Act
        OrderResponseDTO response = orderService.getOrderById(2L);

        // Assert
        assertNotNull(response);
        assertEquals(2L, response.getId());
        assertEquals(StatusOrder.CREATED, response.getStatusOrder());
        assertEquals(StatusPayment.SUCCESS, response.getStatusPayment());
        assertEquals(5L, response.getUserId());
        assertEquals(new BigDecimal("600.00"), response.getAmount());

        verify(orderRepository, times(1)).findOrderById(2L);
    }

    @Test
    @DisplayName("Should throw NoSuchElementException when order not found")
    void shouldThrowWhenOrderNotFound() {
        // Arrange
        when(orderRepository.findOrderById(99L)).thenReturn(Optional.empty());

        // Act + Assert
        assertThrows(RuntimeException.class,
                () -> orderService.getOrderById(99L));

        verify(orderRepository, times(1)).findOrderById(99L);
    }


    @Test
    @DisplayName("Should return Order if id exists")
    void shouldGetOrderById() {
        OrderEntity expectedOrder = new OrderEntity(
                1L,
                StatusOrder.CREATED,
                StatusPayment.SUCCESS,
                5L,
                new BigDecimal(600)
        );

        when(orderRepository.findOrderById(1L)).thenReturn(Optional.of(expectedOrder));

        OrderResponseDTO found = orderService.getOrderById(1L);

        assertNotNull(found);
        verify(orderRepository, times(1)).findOrderById(1L);
    }

    @Test
    @DisplayName("Should throw exception if ID is invalid")
    void shouldThrowWhenInvalidOrderId() {
        assertThrows(RuntimeException.class, () -> orderService.getOrderById(-1L));
    }

    @Test
    @DisplayName("UPDATE status payment to SUCCESS")
    void shouldChangePaymentStatusToSuccess() {
        // Arrange
        OrderEntity entity = new OrderEntity(2L,StatusOrder.CREATED,StatusPayment.PENDING,5L,new BigDecimal(600));
        when(orderRepository.findOrderById(2L)).thenReturn(Optional.of(entity));
        when(orderRepository.save(any(OrderEntity.class))).thenReturn(entity);

        // Act
        OrderEntity orderEntity = orderService.updateStatus(entity.getId(), StatusPayment.SUCCESS);

        // Assert
        assertNotNull(orderEntity);
        assertEquals(StatusPayment.SUCCESS, orderEntity.getStatusPayment());
        assertEquals(StatusPayment.SUCCESS, entity.getStatusPayment());

        verify(orderRepository, times(1)).findOrderById(2L);
        verify(orderRepository, times(1)).save(any(OrderEntity.class));
    }

    @Test
    @DisplayName("Should throw NoSuchElementException when order id not found")
    void shouldThrowWhenOrderIdNotFound() {
        when(orderRepository.findOrderById(99L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class,
                () -> orderService.updateStatus(99L, StatusPayment.SUCCESS));

        verify(orderRepository, times(1)).findOrderById(99L);
        verify(orderRepository, never()).save(any(OrderEntity.class));
    }

    @Test
    @DisplayName("Should throw UserNotFoundException if userId does not exists when order delete method is called.")
    void shouldThrowWhenDeletingWithInvalidOrderId() {
        doThrow(new RuntimeException("DB delete failed")).when(orderRepository).deleteById(999L);

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> orderService.deleteOrder(999L));

        assertInstanceOf(OrderCouldNotDeleteException.class, exception.getCause());

        assertEquals("Could not delete order with ID: 999", exception.getCause().getMessage());

        verify(orderRepository, times(1)).deleteById(999L);
    }

}