package com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.adapter.impls;

import com.ecommerce.wlm_ecommerce_product_service.domain.model.Product;
import com.ecommerce.wlm_ecommerce_product_service.domain.repository.ProductRepository;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.adapter.mapps.ProductMapper;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.entity.ProductEntity;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.repository.SpringDataProduct;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    public SpringDataProduct springDataProduct;

    public ProductRepositoryImpl(SpringDataProduct jpaInterfaceInstance) {
        this.springDataProduct = jpaInterfaceInstance;
    }

    @Override
    public Product save(Product product) {
        ProductEntity entity = ProductMapper.toEntity(product);

        ProductEntity saved = springDataProduct.save(entity);
        return ProductMapper.toDomain(saved);
    }

    @Override
    public Product findById(Long id){
        Optional<ProductEntity> productEntity = springDataProduct.findById(id);
        return ProductMapper.toDomain(productEntity.orElse(null));
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
