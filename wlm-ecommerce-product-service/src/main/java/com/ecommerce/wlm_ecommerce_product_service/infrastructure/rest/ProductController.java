package com.ecommerce.wlm_ecommerce_product_service.infrastructure.rest;

import com.ecommerce.wlm_ecommerce_product_service.application.dto.ProductDTO;
import com.ecommerce.wlm_ecommerce_product_service.application.service.ProductService;
import com.ecommerce.wlm_ecommerce_product_service.domain.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO dto) {
        Product product = dto.toDomain();
        Product saved = productService.create(product);

        return ResponseEntity.ok(ProductDTO.fromDomain(saved));
    }

    @GetMapping("/{sku}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable String sku) {
        return productService.getSkuProduct(sku)
                .map(ProductDTO::fromDomain)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
