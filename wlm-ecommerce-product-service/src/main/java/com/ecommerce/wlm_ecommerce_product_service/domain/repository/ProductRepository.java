package com.ecommerce.wlm_ecommerce_product_service.domain.repository;

import com.ecommerce.wlm_ecommerce_product_service.domain.model.Product;

import java.util.List;

public interface ProductRepository {
    Product save(Product product);
    Product findById(Long id);
    List<Product> findAll();
    void delete(Long id);
}
