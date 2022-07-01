package com.example.pp_3_1_1_springboot.dao;

import com.example.pp_3_1_1_springboot.models.User;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;


@Repository
public class UserDAOImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> index() {
        return entityManager.createQuery(
                "select user from User user", User.class)
                .getResultList();
    }
    @Override
    public User showUser(int id) {
        TypedQuery<User> query = entityManager.createQuery(
                "select u from User u where u.id = :id", User.class);
        query.setParameter("id", id);
        return query.getResultList().stream().findAny().orElse(null);
    }

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public void update(int id, User userUpdated) {
        User user = (User) entityManager.find(User.class, id); //необходимо сократить
        user.setName(userUpdated.getName());
        user.setSurname(userUpdated.getSurname());
    }

    @Override
    public void deleteUser(int id) {
        Query query = entityManager.createQuery(
                "DELETE FROM User u WHERE u.id = :id");     //необходимо сократить
        query.setParameter("id", id).executeUpdate();
    }

}
