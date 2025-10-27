package com.ecommerce.wlm_ecommerce_product_service.application.service;

import com.ecommerce.wlm_ecommerce_product_service.application.dto.UserDTO;
import com.ecommerce.wlm_ecommerce_product_service.domain.exception.UserNotFoundException;
import com.ecommerce.wlm_ecommerce_product_service.domain.model.User;
import com.ecommerce.wlm_ecommerce_product_service.domain.repository.UserRepository;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.adapter.mapps.ProductMapper;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.adapter.mapps.UserMapper;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.entity.ProductEntity;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.entity.UserEntity;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserDTO createUser(UserEntity user){
        UserEntity userEntity = userRepository.save(user);
        if(user == null){
            throw new IllegalArgumentException("Need to provide a valid User");
        }
        User savedUser = UserMapper.toDomain(userEntity);
        return UserDTO.toDTO(savedUser);
    }

    public UserDTO getUserById(@Positive Long id) throws UserNotFoundException {
        UserEntity userEntity = userRepository.findById(id);
        if (userEntity == null) {
            throw new UserNotFoundException("User not found with ID: ");
        }
        User user = UserMapper.toDomain(userEntity);
        return UserDTO.toDTO(user);
    }

    public List<UserDTO> findAll() throws UserNotFoundException {
        List<UserDTO> dtoList = new ArrayList<>();

        for (User user: userRepository.findAll()) {
            dtoList.add(UserDTO.toDTO(user));
        }

        if(dtoList.isEmpty()){
            throw new UserNotFoundException();
        } else {
            return dtoList;
        }
    }

    @Transactional
    public UserDTO updateUser(User updateRequest){
        if(updateRequest == null){
            throw new IllegalArgumentException("Need to provide a valid user");
        }

        UserEntity existingUser = userRepository.findById(updateRequest.getId());

        // USER
        existingUser.setName(updateRequest.getName());
        existingUser.setEmail(updateRequest.getEmail());

        // ADDRESS
        existingUser.getAddressEntity().setStreet(updateRequest.getAddress().getStreet());
        existingUser.getAddressEntity().setCity(updateRequest.getAddress().getCity());
        existingUser.getAddressEntity().setState(updateRequest.getAddress().getState());
        existingUser.getAddressEntity().setZip(updateRequest.getAddress().getZip());

        User savedUser = UserMapper.toDomain(userRepository.save(existingUser));
        return UserDTO.toDTO(savedUser);
    }

    @Transactional
    public void deleteUser(@Positive Long id){
            userRepository.delete(id);
    }

}
