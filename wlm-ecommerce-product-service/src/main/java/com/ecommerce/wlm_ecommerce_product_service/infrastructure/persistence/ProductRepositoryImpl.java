package com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence;

import com.ecommerce.wlm_ecommerce_product_service.domain.model.Product;
import com.ecommerce.wlm_ecommerce_product_service.domain.repository.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    public SpringDataProductJpa springDataProductJpa;

    public ProductRepositoryImpl(SpringDataProductJpa jpaInterfaceInstance){
        this.springDataProductJpa = jpaInterfaceInstance;
    }

    @Override
    public Product save(Product product) {
       ProductEntity entity = ProductMapper.toEntity(product);
       ProductEntity saved = springDataProductJpa.save(entity);

       return ProductMapper.toDomain(saved);
    }

    @Override
    public Optional<Product> findBySku(String sku){
        return springDataProductJpa
                .findBySku(sku)
                .map(ProductMapper::toDomain);
    }

    @Override
    public List<Product> findAll(){
        return springDataProductJpa
                .findAll()
                .stream()
                .map(ProductMapper::toDomain)
                .toList();
    }
}
