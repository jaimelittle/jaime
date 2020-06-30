package com.xjp.dao;


import com.xjp.entity.User;

import java.util.List;

public interface UserDao {

    List<User> findAll();
    void saveUser(User user);
    void updateUser(User user);
    void deleteUser(Integer id);
    User findById(Integer id);
    List<User> findByName(String name);
    int findTotal();
}