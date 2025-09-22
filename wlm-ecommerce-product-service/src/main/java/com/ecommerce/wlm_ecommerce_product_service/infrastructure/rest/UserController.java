package com.ecommerce.wlm_ecommerce_product_service.infrastructure.rest;

import com.ecommerce.wlm_ecommerce_product_service.application.service.UserService;
import com.ecommerce.wlm_ecommerce_product_service.domain.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/find/{id}")
    public ResponseEntity<User> findByIdUser(@PathVariable Long id){
        User user = userService.findById(id);
        if(user == null){
            throw new IllegalArgumentException("User not found");
        }

        return ResponseEntity.ok(user);
    }

    @GetMapping("/{email}")
    public ResponseEntity<User> getUser(@PathVariable String email){
        if(email.isBlank()){
            throw new IllegalArgumentException("User cannot be null");
        }
        User user = userService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        List<User> list = userService.getAllUsers();
        if(list.isEmpty()){
           return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(list);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Long id){
        User newUser = userService.updateUser(user, id);

        if(newUser == null){
            throw new IllegalArgumentException("The user could not be found.");
        }
        return ResponseEntity.ok(newUser);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        if(id <= 0){
            return ResponseEntity.badRequest().build();
        }

        boolean deleted = userService.deleteUser(id);
        if(!deleted){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

}
