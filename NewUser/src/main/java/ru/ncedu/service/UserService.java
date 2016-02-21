package ru.ncedu.service;

import ru.ncedu.entity.User;
import ru.ncedu.entity.UserType;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Gamzat on 03.12.2015.
 */
public class UserService {
    public static EntityManager em = Persistence.createEntityManagerFactory("NCEDU").createEntityManager();

    public static List<User> getAllUsers() {
        return em.createNamedQuery("User.getAllUsers", User.class).getResultList();
    }

    public static List<UserType> getAllTypes(){
        return em.createNamedQuery("UserType.getTypes", UserType.class).getResultList();
    }

    public static User addUser(User user){
        em.getTransaction().begin();
        User result = em.merge(user);
        em.getTransaction().commit();
        return result;
    }

    public static UserType addUserType(UserType uT){
        em.getTransaction().begin();
        UserType result = em.merge(uT);
        em.getTransaction().commit();
        return result;
    }
}
