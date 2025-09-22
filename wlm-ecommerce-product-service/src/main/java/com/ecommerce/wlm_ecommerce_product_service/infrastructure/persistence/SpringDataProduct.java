package com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataProduct extends JpaRepository<ProductEntity, Long> {
    ProductEntity findBySku(String sku);
    List<ProductEntity> findByName(String name);
}
