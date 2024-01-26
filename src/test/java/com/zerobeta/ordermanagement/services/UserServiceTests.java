package com.zerobeta.ordermanagement.services;

import com.zerobeta.ordermanagement.dto.SignUpRequest;
import com.zerobeta.ordermanagement.model.User;
import com.zerobeta.ordermanagement.repository.UserRepository;
import com.zerobeta.ordermanagement.securityConfig.UserInfoDetails;
import com.zerobeta.ordermanagement.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        // You can configure additional setup if needed.
    }

    @Test
    void signUpUser_NewUser_Successful() {
        // Arrange
        SignUpRequest signUpRequest = new SignUpRequest("John", "Doe", "john.doe@example.com", "password");
        User mockedUser = new User(); // Set appropriate values for User

        // Mocking
        when(userRepository.findByEmail(signUpRequest.getEmail())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(signUpRequest.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(mockedUser);

        // Act
        String result = userService.signUpUser(signUpRequest);

        // Assert
        assertEquals("SUCCESSFUL", result);
        // Add more assertions based on your requirements.
    }

    @Test
    void signUpUser_UserExists_ReturnsEmailAlreadyExists() {
        // Arrange
        SignUpRequest signUpRequest = new SignUpRequest("John", "Doe", "john.doe@example.com", "password");

        // Mocking
        when(userRepository.findByEmail(signUpRequest.getEmail())).thenReturn(Optional.of(new User()));

        // Act
        String result = userService.signUpUser(signUpRequest);

        // Assert
        assertEquals("EMAIL ALREADY EXISTS", result);
        // Add more assertions based on your requirements.
    }

    @Test
    void loadUserByUsername_UserExists_ReturnsUserInfoDetails() {
        // Arrange
        String username = "john.doe@example.com";

        // Set appropriate values for User
        User user = new User();
        user.setEmail(username);
        user.setPassword("password");
        user.setRoles(Collections.singletonList("ROLE_USER").toString()); // Set appropriate roles

        // Mocking
        when(userRepository.findByEmail(username)).thenReturn(Optional.of(user));

        // Act
        UserInfoDetails result = (UserInfoDetails) userService.loadUserByUsername(username);

        // Assert
        assertNotNull(result, "User details should not be null");
    }





    @Test
    void loadUserByUsername_UserNotFound_ThrowsUsernameNotFoundException() {
        // Arrange
        String username = "nonexistent@example.com";

        // Mocking
        when(userRepository.findByEmail(username)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername(username));
        // Add more assertions based on your requirements.
    }

    // Add more test methods for other scenarios (e.g., invalid inputs, etc.).

}