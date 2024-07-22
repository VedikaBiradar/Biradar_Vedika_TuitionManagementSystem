package com.vedikabiradar.biradar_vedika_tuitionmanagementsystem_casestudy.Entity;


import jakarta.persistence.*;

@Entity
public class ContactMessage {

    //properties
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullname;
    private String email;
    private String phone;
    private String message;

    // Getters and setters and constructors


    public ContactMessage() {
    }

    public ContactMessage(String fullname, String email, String phone, String message) {
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.message = message;
    }

    public ContactMessage(Long id, String fullname, String email, String phone, String message) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.message = message;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    //toString Method
    @Override
    public String toString() {
        return "ContactMessage{" +
                "id=" + id +
                ", fullname='" + fullname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}

