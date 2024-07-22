package com.vedikabiradar.biradar_vedika_tuitionmanagementsystem_casestudy.Service;


import com.vedikabiradar.biradar_vedika_tuitionmanagementsystem_casestudy.Entity.StandardSubject;
import com.vedikabiradar.biradar_vedika_tuitionmanagementsystem_casestudy.Repository.StandardSubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StandardSubjectService {
    @Autowired
    private StandardSubjectRepository standardSubjectRepository;


    //save method to save standard and related subject information
    public void saveStandardSubject(StandardSubject standardSubject) {
        try {
            standardSubjectRepository.save(standardSubject);
        } catch (Exception e) {
            // Handle the exception, log it, or rethrow it
            System.err.println("Error saving StandardSubject: " + e.getMessage());
            // Optionally, you can rethrow the exception or handle it differently
            throw new RuntimeException("Failed to save StandardSubject", e);
        }
    }

    //get information about standard and subject
    public List<StandardSubject> getAllStandardSubjects() {
        try {
            return standardSubjectRepository.findAll();
        } catch (Exception e) {
            // Handle the exception, log it, or return an empty list
            System.err.println("Error retrieving StandardSubjects: " + e.getMessage());
            return List.of(); // Return an empty list or handle as needed
        }
    }

    //find standard and subject information by standard
    public Object findByStandard(String standard) {
        return standardSubjectRepository.findByStandard(standard);
    }
}

