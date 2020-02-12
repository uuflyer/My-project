package com.myProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User {
    private int id;
    private String username;
    @JsonIgnore
    private String encryptedpassword;
    private String tel;
    private String mail;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEncryptedpassword() {
        return encryptedpassword;
    }

    public void setEncryptedpassword(String encryptedpassword) {
        this.encryptedpassword = encryptedpassword;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
