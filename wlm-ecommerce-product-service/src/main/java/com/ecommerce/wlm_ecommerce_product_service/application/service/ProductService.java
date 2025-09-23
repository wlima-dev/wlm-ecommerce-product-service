package com.ecommerce.wlm_ecommerce_product_service.application.service;

import com.ecommerce.wlm_ecommerce_product_service.application.dto.ProductDTO;
import com.ecommerce.wlm_ecommerce_product_service.domain.model.Product;
import com.ecommerce.wlm_ecommerce_product_service.domain.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Product create(Product product) {
        return repository.save(product);
    }

    public ProductDTO findById(Long id){
        Product product = repository.findById(id);
        return ProductDTO.fromDomain(product);
    }

    public ProductDTO getProductBySku(String sku) {
        Product product = repository.findBySku(sku);

        return ProductDTO.fromDomain(product);
    }

    public List<ProductDTO> getProductListByName(String name){
        return repository.findByName(name)
                .stream()
                .map(ProductDTO::fromDomain)
                .collect(Collectors.toList());
    }

    public List<ProductDTO> listAllProduct() {
        return repository.findAll()
                .stream()
                .map(ProductDTO::fromDomain)
                .collect((Collectors.toList()));
    }

    @Transactional
    public ProductDTO updateProduct(Product product){
        Product updated = repository.update(product);

        return ProductDTO.fromDomain(updated);
    }

    @Transactional
    public ProductDTO deleteProduct(String sku){
        if(sku.isBlank()){
            throw new IllegalArgumentException("SKU cannot be blank");
        }
        return ProductDTO.fromDomain(repository.deleteProduct(sku));
    }

}
