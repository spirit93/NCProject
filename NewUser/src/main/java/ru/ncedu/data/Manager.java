package ru.ncedu.data;


import ru.ncedu.entities.Users;
import org.hibernate.usertype.UserType;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

public class Manager {

    public Manager(){
    }
    @PersistenceContext
    public static EntityManager em = Persistence.createEntityManagerFactory("NCEDU").createEntityManager();

    public static Users register(Users users) {
        em.getTransaction().begin();
        Users result = em.merge(users);
        em.getTransaction().commit();
        return result;
    }

    public static Users Login(long id) {
        em.getTransaction().begin();
        Users result = em.find(Users.class, id);
        em.getTransaction().commit();
        return result;
    }

    public static void deleteUsers(long id) {
        em.getTransaction().begin();
        em.remove(Login(id));
        em.getTransaction().commit();
    }

    public static UserType changeType(UserType userType) {
        em.getTransaction().begin();
        UserType result = em.merge(userType);
        em.getTransaction().commit();
        return result;
    }
}
