package com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.adapter.mapps;

import com.ecommerce.wlm_ecommerce_product_service.domain.model.Address;
import com.ecommerce.wlm_ecommerce_product_service.domain.model.User;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.entity.AddressEntity;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.entity.UserEntity;

public class UserMapper {
    public static User toDomain(UserEntity entity){
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
