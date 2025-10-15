package com.ecommerce.wlm_ecommerce_product_service.application.service;

import com.ecommerce.wlm_ecommerce_product_service.domain.exception.UserNotFoundException;
import com.ecommerce.wlm_ecommerce_product_service.domain.model.User;
import com.ecommerce.wlm_ecommerce_product_service.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Positive;
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
            throw new IllegalArgumentException("Need to provide a valid User");
        }

        return userRepository.save(user);
    }

    public User getUserById(@Positive Long id) {
        try{
            User user = userRepository.findById(id);

            if (user == null) {
                throw new UserNotFoundException("User not found with ID: ");
            }
            return user;
        } catch (UserNotFoundException e) {
            System.err.println("User lookup failed: " + e.getMessage());
            return null;
        }
    }

    public List<User> findAll() {
        try{
            List<User> list = userRepository.findAll();
            if(list.isEmpty()){
                throw new UserNotFoundException();
            }
            return list;
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public User updateUser(User updateRequest){
        if(updateRequest == null){
            throw new IllegalArgumentException("Need to provide a valid user");
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

        return userRepository.save(existingUser);
    }

    @Transactional
    public void deleteUser(@Positive Long id){
            userRepository.delete(id);
    }

}
