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

    protected Address() {}

    public Address(String street, String city, String state, String zip) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public void changeStreet(String street) {
        this.street = street;
    }

    public void changeCity(String city) {
        this.city = city;
    }

    public void changeState(String state) {
        this.state = state;
    }

    public void changeZip(String zip) {
        this.zip = zip;
    }
}
