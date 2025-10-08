package com.ecommerce.wlm_ecommerce_product_service.infrastructure.rest.controller;

import com.ecommerce.wlm_ecommerce_product_service.application.dto.ProductDTO;
import com.ecommerce.wlm_ecommerce_product_service.application.service.ProductService;
import com.ecommerce.wlm_ecommerce_product_service.domain.exception.ProductNotFoundException;
import com.ecommerce.wlm_ecommerce_product_service.domain.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping()
    public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO dto) {
        Product product = dto.toDomain(dto);
        Product saved = productService.create(product);
        return ResponseEntity.ok(ProductDTO.fromDomain(saved));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id){
        ProductDTO productDTO = ProductDTO.fromDomain(productService.findById(id));
        return ResponseEntity.ok(productDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductDTO>> listAllProducts() {
        List<ProductDTO> dtoList = new ArrayList<>();

        for(Product product : productService.listAllProduct()){
            dtoList.add(ProductDTO.fromDomain(product));
        }

        return ResponseEntity.ok(dtoList);
    }

    @PutMapping()
    public ResponseEntity<ProductDTO> update(@RequestBody ProductDTO dto) {
        Product updated = productService.updateProduct(ProductDTO.toDomain(dto));
        return ResponseEntity.ok(ProductDTO.fromDomain(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.ok().build();
        } catch (ProductNotFoundException exception){
            return ResponseEntity.notFound().build();
        }
    }

}
