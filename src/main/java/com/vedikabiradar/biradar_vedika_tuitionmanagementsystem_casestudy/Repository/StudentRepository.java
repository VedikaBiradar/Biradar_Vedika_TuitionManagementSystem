package com.vedikabiradar.biradar_vedika_tuitionmanagementsystem_casestudy.Repository;


import com.vedikabiradar.biradar_vedika_tuitionmanagementsystem_casestudy.Entity.Student;
import com.vedikabiradar.biradar_vedika_tuitionmanagementsystem_casestudy.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByUser(User user);

}