package com.vedikabiradar.biradar_vedika_tuitionmanagementsystem_casestudy.Repository;


import com.vedikabiradar.biradar_vedika_tuitionmanagementsystem_casestudy.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    //find role by using name
    Role findByName(String name);
}