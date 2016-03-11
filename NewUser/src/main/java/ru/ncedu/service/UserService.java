package ru.ncedu.service;

import com.sun.istack.internal.Nullable;
import ru.ncedu.entity.User;
import ru.ncedu.entity.UserType;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;

/**
 * Created by Gamzat on 03.12.2015.
 */

@Stateless
public class UserService {
    @PersistenceUnit(unitName="NCEDU")
    public static EntityManager em = Persistence.createEntityManagerFactory("NCEDU").createEntityManager();

    public static List<User> getAllUsers() {
        return em.createNamedQuery("User.getAllUsers", User.class).getResultList();
    }

    public static List<UserType> getAllTypes(){
        return em.createNamedQuery("UserType.getTypes", UserType.class).getResultList();
    }

    @Nullable
    public static User getUserByName(String userName) {
        TypedQuery<User> query = em.createNamedQuery("User.getUserByLogin", User.class);
        query.setParameter("userName", userName);

        User user = null;
        try{
            user = query.getSingleResult();
        }catch (NoResultException ex){
        }

        if (user!= null){
            return user;
        }
        return null;
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