package com.ecommerce.wlm_ecommerce_product_service.application.service;

import com.ecommerce.wlm_ecommerce_product_service.domain.model.User;
import com.ecommerce.wlm_ecommerce_product_service.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User createUser(User user){
        return userRepository.save(user);
    }

    public User findById(Long id){
        return userRepository.findById(id);
    }

    public List<User> findAll(){
        List<User> list = userRepository.findAll();
        if(list.isEmpty()){
            throw new NoSuchElementException("Was not able to find any user!");
        }
        return list;
    }

    @Transactional
    public User updateUser(User user){
        if(user.getId() == null){
            throw new IllegalArgumentException("was not possible to find a register.");
        }

        User existingUser = userRepository.findById(user.getId());
        if(existingUser == null){
            throw new IllegalArgumentException("Cant find this user" + user.getId());
        }

        return userRepository.updateUser(user);
    }

    @Transactional
    public void deleteUser(Long id){
            userRepository.delete(id);
    }

}
