package com.vedikabiradar.biradar_vedika_tuitionmanagementsystem_casestudy.Service;


import com.vedikabiradar.biradar_vedika_tuitionmanagementsystem_casestudy.Dto.UserDto;
import com.vedikabiradar.biradar_vedika_tuitionmanagementsystem_casestudy.Entity.Role;
import com.vedikabiradar.biradar_vedika_tuitionmanagementsystem_casestudy.Entity.User;
import com.vedikabiradar.biradar_vedika_tuitionmanagementsystem_casestudy.Repository.RoleRepository;
import com.vedikabiradar.biradar_vedika_tuitionmanagementsystem_casestudy.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    //constructor
    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    //save user method to save data
    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());

        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhone(userDto.getPhone());
        user.setAddress(userDto.getAddress());
        user.setCity(userDto.getCity());
        user.setState(userDto.getState());
        user.setCountry(userDto.getCountry());
        user.setZip(userDto.getZip());


        //Determine the role based on criteria
        String roleName;
        if(userDto.isAdminRegistration()){
            roleName = "ROLE_ADMIN";
        }else{
            roleName= "ROLE_PARENT";
        }

        //Check if role already exists in database, otherwise create it
        Role role = roleRepository.findByName(roleName);
        if(role == null) {
            role = new Role();
            role.setName((roleName));
            roleRepository.save(role);
        }

        //Assign the role to the user
        user.setRoles((Collections.singletonList(role)));
        userRepository.save(user);
    }



    //find user details by using their email
    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    //get all users
    @Override
    public List<UserDto> findAllUsers() {
        List<User> users= userRepository.findAll();
        return users.stream().map((user) -> convertEntityToDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users=userRepository.findAll();
        return List.of();
    }


    //get user by id
    @Override
    public Optional<User> getUserById(Long parentId) {
        Optional<User> user= userRepository.findById(parentId);
        return user;
    }

    //find user by email
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    //check user email is alreday present or not
    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }


    //delete user by email
    @Override
    public void deleteByEmail(String email) {
        userRepository.deleteByEmail(email);

    }

    private UserDto convertEntityToDto(User user){
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setUserName(user.getUserName());
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPhone(user.getPhone());
        userDto.setAddress(user.getAddress());
        userDto.setCity(user.getCity());
        userDto.setState(user.getState());
        userDto.setCountry(user.getCountry());
        userDto.setZip(user.getZip());

        return userDto;
    }

    //delete parent by email
    @Transactional
    public boolean deleteParentByEmail(String email) {
        var user = userRepository.findByEmail(email);
        if (user != null) {
            user.getRoles().clear();  // Disassociate roles
            userRepository.delete(user);
            return true;
        }
        return false;
    }


}
