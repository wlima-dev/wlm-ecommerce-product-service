package com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.adapter.mapps;

import com.ecommerce.wlm_ecommerce_product_service.domain.model.Product;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.entity.ProductEntity;

public class ProductMapper {

    public static Product toDomain(ProductEntity entity){
        return new Product(
                entity.getSku(),
                entity.getName(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getQuantity()
        );
    }

    public static ProductEntity toEntity(Product product){
        return new ProductEntity(
            product.getSku(),
            product.getName(),
            product.getDescription(),
            product.getPrice(),
            product.getQuantity()
        );
    }
}
