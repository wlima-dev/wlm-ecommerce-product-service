package com.ecommerce.wlm_ecommerce_product_service.infrastructure.rest.controller;

import com.ecommerce.wlm_ecommerce_product_service.application.dto.ProductDTO;
import com.ecommerce.wlm_ecommerce_product_service.application.service.ProductService;
import com.ecommerce.wlm_ecommerce_product_service.domain.exception.ProductNotFoundException;
import com.ecommerce.wlm_ecommerce_product_service.domain.exception.ProductWasNotUpdatedException;
import com.ecommerce.wlm_ecommerce_product_service.domain.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping()
    public ResponseEntity<Product> create(@RequestBody Product product) throws ProductNotFoundException {
        return ResponseEntity.ok(productService.create(product));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) throws ProductNotFoundException {
        return ResponseEntity.ok(productService.findById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> listAllProducts() {
        return ResponseEntity.ok(productService.listAllProduct());
    }

    @PutMapping()
    public ResponseEntity<Product> update(@RequestBody Product product) throws ProductWasNotUpdatedException {
        return ResponseEntity.ok(productService.updateProduct(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable Long id) {
            productService.deleteProduct(id);
            return ResponseEntity.ok().build();
    }

}
