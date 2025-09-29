package com.ecommerce.wlm_ecommerce_product_service.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private Long id;
    private String name;
    private String email;
    private Address address;

    public User(String name, String email, Address address) {
        if(!email.contains("@") || !email.contains(".") ){
            throw new IllegalArgumentException("Invalid email address");
        }

        this.name = name;
        this.email = email;
        this.address = address;
    }

    public User(Long id, String name, String email, Address address) {
        if(!email.contains("@") || !email.contains(".") ){
            throw new IllegalArgumentException("Invalid email address");
        }

        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
    }

}
