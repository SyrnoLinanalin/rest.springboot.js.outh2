package com.akhtyamovfanil.springboot.demo.service;

import com.akhtyamovfanil.springboot.demo.model.User;

import java.util.List;

public interface UserService {
    void add(User user);
    void update(User user);
    void delete(long id);
    User getById(long id);
    User getByName(String username);
    List<User> getAll();

}
