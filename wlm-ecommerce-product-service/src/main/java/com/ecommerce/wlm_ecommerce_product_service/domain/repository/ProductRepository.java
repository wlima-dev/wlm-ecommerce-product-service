package com.ecommerce.wlm_ecommerce_product_service.domain.repository;

import com.ecommerce.wlm_ecommerce_product_service.domain.model.Product;

import java.util.List;

public interface ProductRepository {
    Product save(Product product);
    Product findById(Long id);
    Product findBySku(String sku);
    List<Product> findByName(String name);
    List<Product> findAll();
    Product update(Product product);
    Product deleteProduct(String sku);
}
