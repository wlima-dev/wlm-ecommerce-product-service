package com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence;

import com.ecommerce.wlm_ecommerce_product_service.domain.model.Product;
import com.ecommerce.wlm_ecommerce_product_service.domain.model.ProductQuantity;

public class ProductMapper {

    public static Product toDomain(ProductEntity entity){
        if(entity == null) return null;

        ProductQuantity productQuantity = new ProductQuantity(
                entity.getEntityQuantity().getQuantity()
        );

        return new Product(
                entity.getSKU(),
                entity.getName(),
                entity.getDescription(),
                entity.getPrice(),
                productQuantity
        );
    }

    public static ProductEntity toEntity(Product product){
        if(product == null) return null;

        ProductEntityQuantity quantity =  new ProductEntityQuantity(product.getQuantity().getQuantity());

        return new ProductEntity(
            product.getSKU(),
            product.getName(),
            product.getDescription(),
            product.getPrice(),
            quantity
        );
    }
}
