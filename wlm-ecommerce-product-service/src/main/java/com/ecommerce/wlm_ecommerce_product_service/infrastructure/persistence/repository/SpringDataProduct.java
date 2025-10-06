package com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.repository;

import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataProduct extends JpaRepository<ProductEntity, Long> {

}
