package com.ecommerce.wlm_ecommerce_product_service.application.service;

import com.ecommerce.wlm_ecommerce_product_service.domain.model.Product;
import com.ecommerce.wlm_ecommerce_product_service.domain.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService productService;

    private Product product;

    @BeforeEach
    public void setUp(){
        this.product = new Product(
                2L,
               "GOOD-SKU-001",
               "GoodName",
               "Good Description",
               new BigDecimal("99.99"),
               123
       );
    }

    @Test
    @DisplayName("Should create product successfully")
    void shouldCreateAValidProduct() {
        // Arrange
        when(productRepository.save(any(Product.class))).thenReturn(product);

        // Act
        Product savedProduct = productService.create(product);

        // Assert
        assertNotNull(savedProduct);
        assertEquals(savedProduct.getSku(), product.getSku());
        assertEquals(savedProduct.getName(), product.getName());
        assertEquals(savedProduct.getDescription(), product.getDescription());
        assertEquals(savedProduct.getPrice(), product.getPrice());
        assertEquals(savedProduct.getQuantity(), product.getQuantity());

        verify(productRepository, times(1)).save(product);
    }

    @Test
    @DisplayName("Should return product when found by existing ID")
    void shouldReturnProductIfExists() {
        // Arrange
        when(productRepository.findById(product.getId())).thenReturn(product);

        // Act
        Product found = productService.findById(product.getId());

        // Assert
        assertNotNull(found);
        assertEquals("GOOD-SKU-001", found.getSku());
        verify(productRepository, times(1)).findById(product.getId());
    }

    @Test
    void listAllProduct() {
        when(productRepository.findAll()).thenReturn(List.of(product));

        List<Product> list = productService.listAllProduct();

        assertNotNull(list);
        verify(productRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should update the product Name, Price and Quantity successfully")
    void shouldUpdateProductSuccessfully() {
        // Arrange
        when(productRepository.findById(product.getId())).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);

        product.changeName("brandNewProduct");
        product.changePrice(BigDecimal.valueOf(27.72));
        product.changeQuantity(7);
        productService.updateProduct(product);

        assertNotNull(product);
        assertEquals("brandNewProduct", product.getName());
        assertEquals(BigDecimal.valueOf(27.72), product.getPrice());
        assertEquals(7, product.getQuantity());

        verify(productRepository, times(1)).save(product);
    }

    @Test
    @DisplayName("Should delete product successfully")
    void shouldDeleteProduct() {
        Long nonExistentId = 999L;
        doNothing().when(productRepository).delete(nonExistentId);

        productService.deleteProduct(nonExistentId);

        verify(productRepository, times(1)).delete(nonExistentId);
    }

}