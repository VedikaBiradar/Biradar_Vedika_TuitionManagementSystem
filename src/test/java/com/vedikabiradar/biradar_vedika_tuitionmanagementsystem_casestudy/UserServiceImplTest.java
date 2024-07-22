package com.vedikabiradar.biradar_vedika_tuitionmanagementsystem_casestudy;


import com.vedikabiradar.biradar_vedika_tuitionmanagementsystem_casestudy.Entity.User;
import com.vedikabiradar.biradar_vedika_tuitionmanagementsystem_casestudy.Repository.UserRepository;
import com.vedikabiradar.biradar_vedika_tuitionmanagementsystem_casestudy.Service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {

        User user = new User();
        user.setUserName("user2");
        user.setEmail("user2@example.com");
        user.setPassword("1235");
        user.setFirstName("John");
        user.setLastName("Doe");

        userRepository.save(user);
    }

    @ParameterizedTest
    @ValueSource(strings = {"user2@example.com", "nonexistent@example.com"})
    void testFindByEmail(String email) {
        User user = userService.findUserByEmail(email);

        if (email.equals("user2@example.com")) {
            assertEquals("user2", user.getUserName());
            assertEquals("John", user.getFirstName());
            assertEquals("Doe", user.getLastName());
        } else {
            assertNull(user);
        }
    }
}
