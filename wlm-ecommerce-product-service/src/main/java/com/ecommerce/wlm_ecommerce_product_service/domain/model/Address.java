package com.ecommerce.wlm_ecommerce_product_service.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {

    private String street;
    private String city;
    private String state;
    private String zip;

    public Address(String street, String city, String state, String zip) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                '}';
    }
}
