package com.vedikabiradar.biradar_vedika_tuitionmanagementsystem_casestudy.Repository;

import com.vedikabiradar.biradar_vedika_tuitionmanagementsystem_casestudy.Entity.StandardSubject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StandardSubjectRepository extends JpaRepository<StandardSubject, Long> {
    List<StandardSubject> findByStandard(String standard);
}
