package com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.adapter.impls;

import com.ecommerce.wlm_ecommerce_product_service.domain.model.User;
import com.ecommerce.wlm_ecommerce_product_service.domain.repository.UserRepository;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.adapter.mapps.UserMapper;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.entity.UserEntity;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.repository.SpringDataUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    public SpringDataUser springDataUser;

    public UserRepositoryImpl(SpringDataUser springDataUser) {
        this.springDataUser = springDataUser;
    }

    @Override
    public User save(User user){
        UserEntity userEntity =  UserMapper.toEntity(user);

        userEntity.setAddressEntity(userEntity.getAddressEntity());
        UserEntity entity = springDataUser.save(userEntity);

        return UserMapper.toDomain(entity);
    }

    @Override
    public User findById(Long id){
        UserEntity userEntity = springDataUser.findById(id).orElseThrow();
        return UserMapper.toDomain(userEntity);
    }

    @Override
    public List<User> findAll(){
        return springDataUser
                .findAll()
                .stream()
                .map(UserMapper::toDomain)
                .toList();
    }

    public void delete(Long id){
        UserEntity userEntity = springDataUser.findById(id).orElseThrow();
        springDataUser.delete(userEntity);
    }

}
