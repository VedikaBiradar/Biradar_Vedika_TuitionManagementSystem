package com.vedikabiradar.biradar_vedika_tuitionmanagementsystem_casestudy.Repository;


import com.vedikabiradar.biradar_vedika_tuitionmanagementsystem_casestudy.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    //find user by using email
    User findByEmail(String email);

    //check user present or not for provided email
    boolean existsByEmail(String email);

    //delete user by email
    void deleteByEmail(String email);
}
