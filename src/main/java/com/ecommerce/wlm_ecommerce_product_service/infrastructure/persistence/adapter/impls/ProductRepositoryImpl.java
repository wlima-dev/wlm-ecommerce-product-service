package com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.adapter.impls;

import com.ecommerce.wlm_ecommerce_product_service.domain.model.Product;
import com.ecommerce.wlm_ecommerce_product_service.domain.repository.ProductRepository;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.adapter.mapps.ProductMapper;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.entity.ProductEntity;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.repository.SpringDataProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final SpringDataProduct springDataProduct;

    @Override
    public ProductEntity save(ProductEntity product) {
        return springDataProduct.save(product);
    }

    @Override
    public ProductEntity findById(Long id){
        return springDataProduct.findById(id).orElseThrow();
    }

    @Override
    public List<Product> findAll(){
        return springDataProduct
                .findAll()
                .stream()
                .map(ProductMapper::toDomain)
                .toList();
    }

    @Override
    public void delete(Long id){
        ProductEntity entity = springDataProduct.findById(id).orElseThrow();
        springDataProduct.delete(entity);
    }

}
