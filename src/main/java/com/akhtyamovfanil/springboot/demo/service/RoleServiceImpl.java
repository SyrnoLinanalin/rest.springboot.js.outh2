package com.akhtyamovfanil.springboot.demo.service;

import com.akhtyamovfanil.springboot.demo.DAO.RoleDao;
import com.akhtyamovfanil.springboot.demo.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl  implements RoleService{

    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }




    @Override
    public List<Role> getRoleList() {
        return roleDao.getRoleList();
    }


}
