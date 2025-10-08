package com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToOne(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private AddressEntity addressEntity;

    public UserEntity() {}

    public UserEntity(String name, String email, AddressEntity addressEntity) {
        this.name = name;
        this.email = email;
        this.addressEntity = addressEntity;
    }


    public void setAddressEntity(AddressEntity addressEntity) {
        this.addressEntity = addressEntity;
        if (addressEntity != null) {
            addressEntity.setUserEntity(this);
        }
    }

}
