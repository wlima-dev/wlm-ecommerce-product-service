package com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String SKU;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal price;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private ProductEntityQuantity entityQuantity;

    protected ProductEntity() {}

    public ProductEntity(String sku, String name, String description, BigDecimal price, ProductEntityQuantity entityQuantity) {
        this.SKU = sku;
        this.name = name;
        this.description = description;
        this.price = price;
        this.entityQuantity = entityQuantity;
    }

}
