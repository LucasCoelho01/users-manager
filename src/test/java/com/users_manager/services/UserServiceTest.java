package com.users_manager.services;

import com.users_manager.entities.User;
import com.users_manager.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser() {
        // Arrange
        String login = "testuser";
        String password = "testpassword";
        String encryptedPassword = "encryptedPassword123";
        when(passwordEncoder.encode(password)).thenReturn(encryptedPassword);

        // Act
        userService.createUser(login, password);

        // Assert
        verify(passwordEncoder, times(1)).encode(password);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testListAllUsers() {
        // Arrange
        List<User> userList = new ArrayList<>();
        userList.add(new User("user1", "password1"));
        userList.add(new User("user2", "password2"));
        when(userRepository.findAll()).thenReturn(userList);

        // Act
        List<User> result = userService.listAllUsers();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(userRepository, times(1)).findAll();
    }
}
