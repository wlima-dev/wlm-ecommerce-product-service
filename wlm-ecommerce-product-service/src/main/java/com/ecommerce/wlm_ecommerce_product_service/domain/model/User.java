package com.ecommerce.wlm_ecommerce_product_service.domain.model;

import lombok.Getter;

@Getter
public class User {
    private Long id;
    private String name;
    private String email;
    private Address address;

    public User(String name, String email, Address address) {
        validate(name,email);

        this.name = name;
        this.email = email;
        this.address = address;
    }

    public User(Long id, String name, String email, Address address) {
        validate(name,email);

        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public void validate(String name, String email){
        if(!email.contains("@") || !email.contains(".") ){
            throw new IllegalArgumentException("Invalid email address");
        }
        if(name.trim().isBlank()){
            throw new IllegalArgumentException("Name cannot be blank");
        }
    }

    public void changeName(String name){
        if(name.trim().isBlank()){
            throw new IllegalArgumentException("Name cannot be blank");
        }
        this.name = name;
    }

    public void changeEmail(String email){
        if(!email.contains("@") || !email.contains(".") ){
            throw new IllegalArgumentException("Invalid email address");
        }
        this.email = email;
    }
}
