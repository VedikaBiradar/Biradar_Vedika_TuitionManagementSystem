package com.vedikabiradar.biradar_vedika_tuitionmanagementsystem_casestudy.Service;

import com.vedikabiradar.biradar_vedika_tuitionmanagementsystem_casestudy.Dto.UserDto;
import com.vedikabiradar.biradar_vedika_tuitionmanagementsystem_casestudy.Entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface UserService {
    void saveUser(UserDto userDto);
    User findUserByEmail(String email);
    List<UserDto> findAllUsers();

    List<User> getAllUsers();

    Optional<User> getUserById(Long parentId);

    User findByEmail(String email);

    boolean existsByEmail(String email);

    void deleteByEmail(String email);

    boolean deleteParentByEmail(String email);
}