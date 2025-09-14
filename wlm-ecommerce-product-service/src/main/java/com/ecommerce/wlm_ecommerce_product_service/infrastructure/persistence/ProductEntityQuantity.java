package com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "product_quantity")
public class ProductEntityQuantity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    @OneToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    public ProductEntityQuantity(){}
    public ProductEntityQuantity(int quantity){
        this.quantity = quantity;
    }
}
