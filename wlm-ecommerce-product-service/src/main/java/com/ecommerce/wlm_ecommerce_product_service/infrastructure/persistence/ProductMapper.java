package com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence;

import com.ecommerce.wlm_ecommerce_product_service.domain.model.Product;

public class ProductMapper {

    public static Product toDomain(ProductEntity entity){
        if(entity == null) return null;

        return new Product(
                entity.getSku(),
                entity.getName(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getQuantity()
        );
    }

    public static ProductEntity toEntity(Product product){
        if(product == null) return null;

        return new ProductEntity(
            product.getSKU(),
            product.getName(),
            product.getDescription(),
            product.getPrice(),
            product.getQuantity()
        );
    }
}
