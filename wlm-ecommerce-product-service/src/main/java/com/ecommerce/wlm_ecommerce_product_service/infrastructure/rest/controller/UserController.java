package com.ecommerce.wlm_ecommerce_product_service.infrastructure.rest.controller;

import com.ecommerce.wlm_ecommerce_product_service.application.dto.UserDTO;
import com.ecommerce.wlm_ecommerce_product_service.application.service.UserService;
import com.ecommerce.wlm_ecommerce_product_service.domain.exception.UserNotFoundException;
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
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO dto) {
        User user = UserDTO.toDomain(dto);
        return ResponseEntity.ok(UserDTO.toDTO(userService.createUser(user)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findByIdUser(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
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
    public ResponseEntity<User> updateUser(@RequestBody UserDTO dto){
        User newUser = userService.updateUser(UserDTO.toDomain(dto));

        if(newUser == null){
            throw new UserNotFoundException(null);
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
