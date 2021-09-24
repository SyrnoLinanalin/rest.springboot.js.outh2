package com.akhtyamovfanil.springboot.demo.DAO;

import com.akhtyamovfanil.springboot.demo.model.User;

import java.util.List;

public interface UserDao {

    void add(User user);
    void update( User user);
    void delete(long id);
    User getById(long id);
    List<User> getAll();
    User getUserByName(String username);
}
