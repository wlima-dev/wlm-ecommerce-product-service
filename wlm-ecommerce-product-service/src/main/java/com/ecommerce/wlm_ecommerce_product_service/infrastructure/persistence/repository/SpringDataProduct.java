package com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.repository;

import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataProduct extends JpaRepository<ProductEntity, Long> {
    ProductEntity findBySku(String sku);
    List<ProductEntity> findByName(String name);
}
