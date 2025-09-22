package com.ecommerce.wlm_ecommerce_product_service.infrastructure.rest;

import com.ecommerce.wlm_ecommerce_product_service.application.dto.ProductDTO;
import com.ecommerce.wlm_ecommerce_product_service.application.service.ProductService;
import com.ecommerce.wlm_ecommerce_product_service.domain.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO dto) {
        Product product = dto.toDomain();
        Product saved = productService.create(product);
        return ResponseEntity.ok(ProductDTO.fromDomain(saved));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id){
        ProductDTO productDTO = productService.findById(id);

        if(productDTO == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productDTO);
    }

    @GetMapping("/sku/{sku}")
    public ResponseEntity<?> getProduct(@PathVariable String sku) {
        ProductDTO product = productService.getProductBySku(sku);

        if(product == null){
            Map<String, Object> response = new LinkedHashMap<>();
            response.put("timestamp: ", LocalDateTime.now());
            response.put("status: ", 404);
            response.put("erro: ", "Not found");
            response.put("message: ", "No Product was found for this SKU: "+ sku);
            response.put("path", "/api/products/sku/ "+ sku);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        return ResponseEntity.ok(product);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<ProductDTO>> getProductByName(@PathVariable String name) {
        List<ProductDTO> products = productService.getProductListByName(name);

        if(products.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(products);
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductDTO>> listAllProducts() {
        List<ProductDTO> products = productService.listAllProduct();

        if(products == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(products);
        }

        return ResponseEntity.ok(productService.listAllProduct());
    }

    @PutMapping("/update")
    public ResponseEntity<ProductDTO> update(@RequestBody ProductDTO dto) {
        Product product = dto.toDomain();

        ProductDTO updated = productService.updateProduct(product);

        if(updated == null){
            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("delete/{sku}")
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable String sku) {
        ProductDTO dto = productService.deleteProduct(sku);
        return ResponseEntity.ok(dto);
    }

}
