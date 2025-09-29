package com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.adapter;

import com.ecommerce.wlm_ecommerce_product_service.domain.exception.UserNotFoundException;
import com.ecommerce.wlm_ecommerce_product_service.domain.model.User;
import com.ecommerce.wlm_ecommerce_product_service.domain.repository.UserRepository;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.entity.UserEntity;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.repository.SpringDataUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;

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
        UserEntity userEntity = springDataUser.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
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

    public User updateUser(User user){
        UserEntity userEntity = springDataUser.findById(user.getId())
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        // USER
        userEntity.setName(user.getName());
        userEntity.setEmail(user.getEmail());

        // ADDRESS
        userEntity.getAddressEntity().setStreet(user.getAddress().getStreet());
        userEntity.getAddressEntity().setCity(user.getAddress().getCity());
        userEntity.getAddressEntity().setState(user.getAddress().getState());
        userEntity.getAddressEntity().setZip(user.getAddress().getZip());

        UserEntity newUserEntity = springDataUser.save(userEntity);

        return UserMapper.toDomain(newUserEntity);
    }

    public void delete(Long id){
        UserEntity userEntity = springDataUser.findById(id)
                        .orElseThrow(() -> new NoSuchElementException("User not found"));
        springDataUser.delete(userEntity);
    }

}
