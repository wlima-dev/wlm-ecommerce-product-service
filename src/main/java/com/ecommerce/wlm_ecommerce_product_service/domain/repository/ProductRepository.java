package com.ecommerce.wlm_ecommerce_product_service.domain.repository;

import com.ecommerce.wlm_ecommerce_product_service.domain.model.Product;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.entity.ProductEntity;

import java.util.List;

public interface ProductRepository {
    ProductEntity save(ProductEntity product);
    ProductEntity findById(Long id);
    List<Product> findAll();
    void delete(Long id);
}
