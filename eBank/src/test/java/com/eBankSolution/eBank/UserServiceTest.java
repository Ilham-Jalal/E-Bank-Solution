package com.eBankSolution.eBank;
//
//import com.eBankSolution.eBank.Repository.UserRepository;
//import com.eBankSolution.eBank.Services.UserService;
//import com.eBankSolution.eBank.models.User;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class UserServiceTest {
//
//    @Mock
//    private UserRepository userRepository;
//
//    @InjectMocks
//    private UserService userService;
//
//    private User user;
//
//    @BeforeEach
//    void setUp() {
//        user = new User(1, "John", "Doe", "john.doe@example.com");
//    }
//
//    @Test
//    void testGetAllUsers() {
//        List<User> users = Arrays.asList(user);
//        when(userRepository.findAll()).thenReturn(users);
//
//        List<User> result = userService.getAllUsers();
//        assertEquals(1, result.size());
//        assertEquals("John", result.get(0).getUserName());
//    }
//
//    @Test
//    void testSaveUser() {
//        when(userRepository.save(user)).thenReturn(user);
//
//        User result = userService.saveUser(user);
//        assertNotNull(result);
//        assertEquals("John", result.getUserName());
//    }
//
//    @Test
//    void testGetUserById() {
//        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
//
//        User result = userService.getUserById(1L);
//        assertNotNull(result);
//        assertEquals("John", result.getUserName());
//    }
//
//    @Test
//    void testGetUserById_NotFound() {
//        when(userRepository.findById(1L)).thenReturn(Optional.empty());
//
//        User result = userService.getUserById(1L);
//        assertNull(result);
//    }
//}
//
