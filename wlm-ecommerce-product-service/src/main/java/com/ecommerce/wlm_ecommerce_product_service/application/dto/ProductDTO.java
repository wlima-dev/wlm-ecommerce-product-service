package com.ecommerce.wlm_ecommerce_product_service.application.dto;

import com.ecommerce.wlm_ecommerce_product_service.domain.model.Product;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductDTO {
    private String sku;
    private String name;
    private String description;
    private BigDecimal price;
    private int quantity;

    public ProductDTO(String sku, String name, String description, BigDecimal price, int quantity) {
        this.sku = sku;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public static ProductDTO fromDomain(Product product){
        return new ProductDTO(
            product.getSku(),
            product.getName(),
            product.getDescription(),
            product.getPrice(),
            product.getQuantity()
        );
    }

    public static Product toDomain(ProductDTO dto) {
        return new Product(
                dto.sku,
                dto.name,
                dto.description,
                dto.price,
                dto.quantity
        );
    }

}
