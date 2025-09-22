package com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence;

import com.ecommerce.wlm_ecommerce_product_service.domain.model.Product;
import com.ecommerce.wlm_ecommerce_product_service.domain.repository.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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
    public Product findBySku(String sku){
        return ProductMapper.toDomain(springDataProduct.findBySku(sku));
    }

    @Override
    public List<Product> findByName(String name){
        List<ProductEntity> listEntity = springDataProduct.findByName(name);

        List<Product> oldWayList = new ArrayList<>();

        for(ProductEntity p : listEntity) {
            oldWayList.add(ProductMapper.toDomain(p));
        }

        return oldWayList;
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
    public Product update(Product product) {
        ProductEntity found = springDataProduct.findBySku(product.getSKU());

        found.setName(product.getName());
        found.setDescription(product.getDescription());
        found.setPrice(product.getPrice());
        found.setQuantity(product.getQuantity());

        ProductEntity saved = springDataProduct.save(found);
        return ProductMapper.toDomain(saved);
    }

    @Override
    public Product deleteProduct(String sku){
        ProductEntity entity = springDataProduct.findBySku(sku);

        if(entity == null) {
            throw new IllegalArgumentException("This something wrong with SKU provided");
        }

        springDataProduct.delete(entity);
        return ProductMapper.toDomain(entity);
    }

}
