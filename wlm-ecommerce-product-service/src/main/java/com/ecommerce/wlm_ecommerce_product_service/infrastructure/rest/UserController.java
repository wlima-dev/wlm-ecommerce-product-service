package com.ecommerce.wlm_ecommerce_product_service.infrastructure.rest;

import com.ecommerce.wlm_ecommerce_product_service.application.service.UserService;
import com.ecommerce.wlm_ecommerce_product_service.domain.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/user")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody User user) {
        if(user == null){
            throw new IllegalArgumentException("User cannot be null");
        }
        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findByIdUser(@PathVariable Long id){
        User user = userService.findById(id);
        if(user == null){
            throw new IllegalArgumentException("User not found");
        }

        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        List<User> list = userService.findAll();
        if(list.isEmpty()){
           return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(list);
    }

    @PutMapping()
    public ResponseEntity<User> updateUser(@RequestBody User user){
        User newUser = userService.updateUser(user);

        if(newUser == null){
            throw new IllegalArgumentException("The user could not be found.");
        }
        return ResponseEntity.ok(newUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        if(id <= 0){
            return ResponseEntity.badRequest().build();
        }

        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException exception){
            return ResponseEntity.notFound().build();
        }
    }

}
