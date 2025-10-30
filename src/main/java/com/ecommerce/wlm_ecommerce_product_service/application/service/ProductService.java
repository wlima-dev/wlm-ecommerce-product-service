package com.ecommerce.wlm_ecommerce_product_service.application.service;

import com.ecommerce.wlm_ecommerce_product_service.domain.exception.ProductNotFoundException;
import com.ecommerce.wlm_ecommerce_product_service.domain.exception.ProductWasNotUpdatedException;
import com.ecommerce.wlm_ecommerce_product_service.domain.model.Product;
import com.ecommerce.wlm_ecommerce_product_service.domain.repository.ProductRepository;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.adapter.mapps.ProductMapper;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.entity.ProductEntity;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional
    public Product create(Product product) throws ProductNotFoundException {
        if(product == null){
            throw new ProductNotFoundException("Invalid product was provided");
        }

        ProductEntity productEntity = ProductMapper.toEntity(product);
        return ProductMapper.toDomain(productRepository.save(productEntity));
    }

    public Product findById(@Positive Long id) throws ProductNotFoundException {
       ProductEntity entity = productRepository.findById(id);
       if(entity == null){
           throw new ProductNotFoundException("Product not found");
       }
       return ProductMapper.toDomain(entity);
    }

    public List<Product> listAllProduct() {
        try{
            List<Product> prodList = productRepository.findAll();
            if (prodList == null) {
                throw new ProductNotFoundException("Could not find any product");
            }
            return prodList;
        } catch (ProductNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public Product updateProduct(Product product) throws ProductWasNotUpdatedException {

        ProductEntity found = productRepository.findById(product.getId());

        found.setName(product.getName());
        found.setPrice(product.getPrice());
        found.setQuantity(product.getQuantity());

        ProductEntity updated = productRepository.save(found);

        return ProductMapper.toDomain(updated);
    }

    @Transactional
    public void deleteProduct(@Positive Long id){
        productRepository.delete(id);
    }

}
