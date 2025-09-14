package com.ecommerce.wlm_ecommerce_product_service.domain.repository;

import com.ecommerce.wlm_ecommerce_product_service.domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Product save(Product product);
    Optional<Product> findBySku(String sku);
    List<Product> findAll();
}
