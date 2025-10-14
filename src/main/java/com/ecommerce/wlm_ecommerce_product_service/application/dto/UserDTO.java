package com.ecommerce.wlm_ecommerce_product_service.application.dto;

import com.ecommerce.wlm_ecommerce_product_service.domain.model.Address;
import com.ecommerce.wlm_ecommerce_product_service.domain.model.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDTO {

    private final String name;
    private final String email;
    private final Address address;

    public UserDTO(String name, String email, Address address) {
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public static User toDomain(UserDTO dto){
        return new User(
                 dto.getName(),
                dto.getEmail(),
                dto.getAddress()
        );
    }

    public static UserDTO toDTO(User user){
        return new UserDTO(
                user.getName(),
                user.getEmail(),
                user.getAddress()
        );
    }

}
