package com.ecommerce.wlm_ecommerce_product_service.application.service;

import com.ecommerce.wlm_ecommerce_product_service.domain.model.Product;
import com.ecommerce.wlm_ecommerce_product_service.domain.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class ProductService {
    ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product create(Product product) {
        return repository.save(product);
    }

    public Optional<Product> getSkuProduct(String sku) {
        return repository.findBySku(sku);
    }

    public List<Product> listAllProduct() {
        return repository.findAll();
    }

    public Product changePrice(String sku, BigDecimal newPrice) {
        Product product = repository.findBySku(sku)
                .orElseThrow( () -> new IllegalArgumentException("Product not found."));

        Product updated = product.changePrice(newPrice);

        return repository.save(updated);
    }
}
