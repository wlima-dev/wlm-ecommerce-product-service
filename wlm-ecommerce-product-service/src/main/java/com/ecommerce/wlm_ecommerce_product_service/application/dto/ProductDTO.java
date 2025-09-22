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
            product.getSKU(),
            product.getName(),
            product.getDescription(),
            product.getPrice(),
            product.getQuantity()
        );
    }

    public Product toDomain() {
        return new Product(
            this.sku,
            this.name,
            this.description,
            this.price,
            this.quantity
        );
    }

}
