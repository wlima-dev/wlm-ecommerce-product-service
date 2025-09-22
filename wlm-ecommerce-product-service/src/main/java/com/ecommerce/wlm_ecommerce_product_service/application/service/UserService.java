package com.ecommerce.wlm_ecommerce_product_service.application.service;

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
        return userRepository.save(user);
    }

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User findById(Long id){
        return userRepository.findById(id);
    }

    public List<User> getAllUsers(){
        return userRepository.findAllUsers();
    }

    @Transactional
    public User updateUser(User user, Long id){
        User upado = userRepository.updateUser(user, id);

        if(upado == null){
            throw new IllegalArgumentException("was not possible to find a register.");
        }

        return upado;
    }

    @Transactional
    public boolean deleteUser(Long id){
        User user = userRepository.findById(id);
        if(user != null){
            userRepository.delete(id);
            return true;
        }
        return false;
    }

}
