package com.ecommerce.wlm_ecommerce_product_service.application.service;

import com.ecommerce.wlm_ecommerce_product_service.domain.exception.UserNotFoundException;
import com.ecommerce.wlm_ecommerce_product_service.domain.model.Address;
import com.ecommerce.wlm_ecommerce_product_service.domain.model.User;
import com.ecommerce.wlm_ecommerce_product_service.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private String name;
    private String email;

    private User user;
    private Address address;
    private Long userId;

    @BeforeEach
    void setUp() {
        // Arrange
        userId = 1L;
        name = "Ivar the boneless";
        email = "vikings@business.email.com";
        address = new Address(
                "Evjenvegen",
                "Tromso",
                "Troms og Finnmark",
                "9024");

        user = new User(name, email, address);
    }

    @Test
    @DisplayName("Should create a valid user")
    void shouldCreateValidUser(){

        when(userRepository.save(user)).thenReturn(user);

        User userCreated = userService.createUser(user);

        assertNotNull(userCreated);
        assertEquals(name, userCreated.getName());
        assertEquals(email, userCreated.getEmail());

        verify(userRepository, times(1)).save(user);
    }

    @Test
    @DisplayName("The email must be invalid and throw an exception")
    void emailShouldBeInvalid(){
        String wrongEmail = "invalid-email.com";

        // Assert
        assertThrows(IllegalArgumentException.class,
                () -> new User(name, wrongEmail, address));
    }

    @Test
    @DisplayName("Check if the findById() method is working")
    void shouldReturnValidUser(){
        // Arrange
        when(userRepository.findById(userId)).thenReturn(user);

        // Act
        User foundUser = userService.getUserById(userId);

        // Assert
        assertNotNull(foundUser);
        assertEquals("Ivar the boneless", foundUser.getName());
        assertEquals("vikings@business.email.com", foundUser.getEmail());

        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    @DisplayName("Should throw UserNotFoundException when findById is called with non-existent ID")
    void shouldThrowUserNotFoundException(){
        Long nonExistentId = 999L;
        when(userRepository.findById(nonExistentId)).thenReturn(null);

        assertThrows(UserNotFoundException.class,
            () -> userService.getUserById(nonExistentId)
        );

        verify(userRepository, times(1)).findById(nonExistentId);
    }

    @Test
    @DisplayName("Should return all users when findAllUsers() is called")
    void shouldReturnAllUsers(){
        User user1 = new User("Ragnar Lothbrok", "ragnar@vikings.com",
                new Address("Main Street", "Oslo", "Oslo", "1000"));
        User user2 = new User("Lagertha", "lagertha@vikings.com",
                new Address("Viking Road", "Bergen", "Vestland", "5000"));

        List<User> users = List.of(user1,user2);

        when(userRepository.findAll()).thenReturn(users);

        List<User> foundUsers = userService.findAll();

        assertNotNull(foundUsers);
        assertEquals(2, foundUsers.size());
        assertEquals("Ragnar Lothbrok", foundUsers.get(0).getName());
        assertEquals("Lagertha", foundUsers.get(1).getName());

        verify(userRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should update user successfully")
    void shouldUpdateTheUser(){
        Long id = 1L;

        User existingUser = new User(id, "Ragnar Lothbrok", "ragnar@vikings.com",
                new Address("Main Street", "Oslo", "Oslo", "1000"));

        User updatedUser = new User(id, "Ragnar Lodbrok", "lodbrok@vikings.com",
                new Address("New Road", "Bergen", "Vestland", "5000"));

        when(userRepository.findById(id)).thenReturn(existingUser);
        when(userRepository.save(any(User.class))).thenReturn(updatedUser);

        User result = userService.updateUser(updatedUser);

        assertNotNull(result, "Assert that user was updated successfully");
        assertEquals("Ragnar Lodbrok", result.getName());
        assertEquals("lodbrok@vikings.com", result.getEmail());
        assertEquals("New Road", result.getAddress().getStreet());

        verify(userRepository, times(1)).findById(id);
        verify(userRepository, times(1)).save(updatedUser);
    }

    @Test
    @DisplayName("Should delete user successfully")
    void shouldDeleteSuccessfully(){
        Long id = 1L;
        doNothing().when(userRepository).delete(id);

        userService.deleteUser(id);
        verify(userRepository, times(1)).delete(id);
    }

}