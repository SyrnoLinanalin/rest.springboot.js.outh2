package com.akhtyamovfanil.springboot.demo.service;

import com.akhtyamovfanil.springboot.demo.DAO.RoleDao;
import com.akhtyamovfanil.springboot.demo.DAO.UserDao;
import com.akhtyamovfanil.springboot.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class UserServiceImpl implements UserService, UserDetailsService {


    private RoleService roleService;
    private UserDao userDao;
    private RoleDao roleDao;

    public UserServiceImpl(RoleService roleService, UserDao userDao, RoleDao roleDao) {
        this.roleService = roleService;
        this.userDao = userDao;
        this.roleDao = roleDao;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.getUserByName(username);
    }

    @Override
    @Transactional(readOnly = true)
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    @Transactional(readOnly = true)
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    @Transactional(readOnly = true)
    public void delete(long id) {
        userDao.delete(id);
    }

    @Override
    @Transactional
    public User getById(long id) {
        return this.userDao.getById(id);
    }

    @Override
    @Transactional
    public User getByName(String username) {
        return this.userDao.getUserByName(username);
    }

    @Override
    @Transactional
    public List<User> getAll() {
        return userDao.getAll();
    }


}
