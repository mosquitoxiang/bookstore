package com.example.administrator.modelmall.entity;

public class LoginData {

    private String email;

    private String nickName;

    public LoginData(String email, String nickName) {
        this.email = email;
        this.nickName = nickName;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
