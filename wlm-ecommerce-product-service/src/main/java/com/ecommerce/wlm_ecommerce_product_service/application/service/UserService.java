package com.ecommerce.wlm_ecommerce_product_service.application.service;

import com.ecommerce.wlm_ecommerce_product_service.domain.exception.UserNotFoundException;
import com.ecommerce.wlm_ecommerce_product_service.domain.model.User;
import com.ecommerce.wlm_ecommerce_product_service.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User createUser(User user){
        if(user == null){
            throw new UserNotFoundException(null);
        }

        return userRepository.save(user);
    }

    public User getUserById(Long id){
        if(id <= 0){
            throw new IllegalArgumentException("invalid id value");
        }

        User user = userRepository.findById(id);
        if(user == null){
            throw new UserNotFoundException(id);
        }

        return user;
    }

    public List<User> findAll(){
        List<User> list = userRepository.findAll();
        if(list.isEmpty()){
            throw new UserNotFoundException();
        }
        return list;
    }

    @Transactional
    public User updateUser(User updateRequest){
        if(updateRequest == null){
            throw new UserNotFoundException(null);
        }

        User existingUser = userRepository.findById(updateRequest.getId());

        // USER
        existingUser.changeName(updateRequest.getName());
        existingUser.changeEmail(updateRequest.getEmail());

        // ADDRESS
        existingUser.getAddress().changeStreet(updateRequest.getAddress().getStreet());
        existingUser.getAddress().changeCity(updateRequest.getAddress().getCity());
        existingUser.getAddress().changeState(updateRequest.getAddress().getState());
        existingUser.getAddress().changeZip(updateRequest.getAddress().getZip());

        return userRepository.save(updateRequest);
    }

    @Transactional
    public void deleteUser(Long id){
        if(id <= 0){
            throw new UserNotFoundException(id);
        }
        userRepository.delete(id);
    }

}
