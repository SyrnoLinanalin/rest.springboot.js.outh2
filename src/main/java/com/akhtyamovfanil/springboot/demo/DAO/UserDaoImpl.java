package com.akhtyamovfanil.springboot.demo.DAO;

import com.akhtyamovfanil.springboot.demo.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void add(User user) {
        entityManager.persist(user);

    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public void delete(long id) {
        entityManager.remove(getById(id));

    }

    @Override
    public User getById(long id) {
        return entityManager.createQuery("FROM User u JOIN FETCH u.roles WHERE u.id = :id", User.class).setParameter("id", id).getSingleResult();
    }

    @Override
    public List<User> getAll() {
        return entityManager.createQuery("SELECT u FROM User u join fetch u.roles").getResultList();
    }

    @Override
    public User getUserByName(String username) {
        return (User) entityManager.createQuery("SELECT u FROM User u join fetch u.roles where u.username = :username", User.class)
                                    .setParameter("username", username)
                                    .getSingleResult();
    }
}
