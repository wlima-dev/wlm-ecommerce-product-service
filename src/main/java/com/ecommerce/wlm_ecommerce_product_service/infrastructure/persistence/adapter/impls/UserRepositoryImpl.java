package com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.adapter.impls;

import com.ecommerce.wlm_ecommerce_product_service.domain.model.User;
import com.ecommerce.wlm_ecommerce_product_service.domain.repository.UserRepository;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.adapter.mapps.UserMapper;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.entity.UserEntity;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.repository.SpringDataUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    public SpringDataUser springDataUser;

    @Override
    public UserEntity save(UserEntity user){
        return springDataUser.save(user);
    }

    @Override
    public UserEntity findById(Long id){
        return springDataUser.findById(id).orElseThrow();
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
