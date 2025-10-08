package com.ecommerce.wlm_ecommerce_product_service.application.service;

import com.ecommerce.wlm_ecommerce_product_service.domain.exception.ProductNotFoundException;
import com.ecommerce.wlm_ecommerce_product_service.domain.model.Product;
import com.ecommerce.wlm_ecommerce_product_service.domain.repository.ProductRepository;
import jakarta.transaction.Transactional;
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
        Product prod =  productRepository.save(product);
        if(prod == null){
            throw new ProductNotFoundException("Could not find this product");
        }
        return prod;
    }

    public Product findById(Long id){
        Product product =  productRepository.findById(id);
        if(product == null){
            throw new ProductNotFoundException(id);
        }
       return product;
    }

    public List<Product> listAllProduct() {
        List<Product> prodList = productRepository.findAll();
        if(prodList == null){
            throw new ProductNotFoundException("Could not find any product");
        }
        return prodList;
    }

    @Transactional
    public Product updateProduct(Product product){
        // Its just changing Name, Price and Quantity;
        Product found = productRepository.findById(product.getId());

        found.changeName(product.getName());
        found.changePrice(product.getPrice());
        found.changeQuantity(product.getQuantity());

        Product updated = productRepository.save(found);
        if(updated == null){
            throw new ProductNotFoundException(product.getId());
        }

        return updated;
    }

    @Transactional
    public void deleteProduct(Long id){
        if(id <= 0){
            throw new ProductNotFoundException(id);
        }

        productRepository.delete(id);
    }

}
