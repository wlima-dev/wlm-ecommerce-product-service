package com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence;

import com.ecommerce.wlm_ecommerce_product_service.domain.model.Address;
import com.ecommerce.wlm_ecommerce_product_service.domain.model.User;

public class UserMapper {
    public static User toDomain(UserEntity entity){
        if(entity == null) return null;

        return new User(
                entity.getName(),
                entity.getEmail(),
                new Address(
                        entity.getAddressEntity().getStreet(),
                        entity.getAddressEntity().getCity(),
                        entity.getAddressEntity().getState(),
                        entity.getAddressEntity().getZip()
                )
        );
    }

    public static UserEntity toEntity(User user){
        if(user == null) return null;

        return new UserEntity(
                user.getName(),
                user.getEmail(),
                new AddressEntity(
                        user.getAddress().getStreet(),
                        user.getAddress().getCity(),
                        user.getAddress().getState(),
                        user.getAddress().getZip()
                )
        );
    }
}
