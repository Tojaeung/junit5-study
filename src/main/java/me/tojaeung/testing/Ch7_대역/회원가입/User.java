package me.tojaeung.testing.Ch7_대역.회원가입;

public class User {
    private String id;
    private String passwordd;
    private String email;

    public User(String id, String passwordd, String email) {
        this.id = id;
        this.passwordd = passwordd;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}
