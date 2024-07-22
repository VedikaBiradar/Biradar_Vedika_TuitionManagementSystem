package com.vedikabiradar.biradar_vedika_tuitionmanagementsystem_casestudy.Service;


import com.vedikabiradar.biradar_vedika_tuitionmanagementsystem_casestudy.Entity.ContactMessage;
import com.vedikabiradar.biradar_vedika_tuitionmanagementsystem_casestudy.Repository.ContactMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactMessageService {

    private final ContactMessageRepository contactMessageRepository;

    @Autowired
    public ContactMessageService(ContactMessageRepository contactMessageRepository) {
        this.contactMessageRepository = contactMessageRepository;
    }

    //save method to save message
    public void saveMessage(ContactMessage message) {
        contactMessageRepository.save(message);
    }

    //get method to get all conatct us messages
    public List<ContactMessage> getAllMessages() {
        return contactMessageRepository.findAll();
    }
}


