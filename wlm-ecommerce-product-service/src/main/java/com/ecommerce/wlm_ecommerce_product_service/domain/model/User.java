package com.ecommerce.wlm_ecommerce_product_service.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private String name;
    private String email;
    private Address address;

    public User(String name, String email, Address address) {
        this.name = name;
        this.email = email;
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                '}';
    }
}
