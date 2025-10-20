package com.ecommerce.wlm_ecommerce_product_service.application.service;

import com.ecommerce.wlm_ecommerce_product_service.domain.exception.ProductNotFoundException;
import com.ecommerce.wlm_ecommerce_product_service.domain.exception.ProductWasNotUpdatedException;
import com.ecommerce.wlm_ecommerce_product_service.domain.model.Product;
import com.ecommerce.wlm_ecommerce_product_service.domain.repository.ProductRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public Product create(Product product) {
        if(product == null){
            throw new IllegalArgumentException("Invalid product was provided");
        }
        return productRepository.save(product);
    }

    public Product findById(@Positive Long id){
       try{
           Product product =  productRepository.findById(id);
           if(product == null){
               throw new ProductNotFoundException(id);
           }
           return product;
       } catch (ProductNotFoundException e) {
           throw new RuntimeException(e);
       }
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
    public Product updateProduct(Product product){
        try {
            Product found = productRepository.findById(product.getId());

            found.changeName(product.getName());
            found.changePrice(product.getPrice());
            found.changeQuantity(product.getQuantity());

            Product updated = productRepository.save(found);
            if (updated == null) {
                throw new ProductWasNotUpdatedException("Could not update this product");
            }

            return updated;
        } catch (ProductWasNotUpdatedException e){
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public void deleteProduct(@Positive Long id){
        productRepository.delete(id);
    }

}
