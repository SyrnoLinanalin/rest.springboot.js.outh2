package com.akhtyamovfanil.springboot.demo.DAO;

import com.akhtyamovfanil.springboot.demo.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Repository
public class RoleDaoImpl implements  RoleDao {

    @PersistenceContext
    private EntityManager entityManager;



    @Override
    public List<Role> getRoleList() {

        return entityManager.createQuery( "FROM Role", Role.class).getResultList();

    }


}
