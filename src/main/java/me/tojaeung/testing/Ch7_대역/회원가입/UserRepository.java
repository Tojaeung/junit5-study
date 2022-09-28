package me.tojaeung.testing.Ch7_대역.회원가입;

public interface UserRepository {
    void save(User user);

    User findById(String id);

}
