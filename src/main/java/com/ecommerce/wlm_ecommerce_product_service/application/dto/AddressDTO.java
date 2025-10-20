package com.ecommerce.wlm_ecommerce_product_service.application.dto;

import com.ecommerce.wlm_ecommerce_product_service.domain.model.Address;
import lombok.Getter;

@Getter
public class AddressDTO {
    private final String street;
    private final String city;
    private final String state;
    private final String zip;

    public AddressDTO(String street, String city, String state, String zip) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public static Address toDomain(AddressDTO dto){
        return new Address(
            dto.getStreet(),
            dto.getCity(),
            dto.getState(),
            dto.getZip()
        );
    }

    public static AddressDTO fromDomain(Address address){
        return new AddressDTO(
                address.getStreet(),
                address.getCity(),
                address.getState(),
                address.getZip()
        );
    }

}
