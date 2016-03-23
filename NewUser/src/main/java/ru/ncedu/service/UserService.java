package ru.ncedu.service;

import com.sun.istack.internal.Nullable;
import ru.ncedu.entity.User;
import ru.ncedu.entity.UserType;
import ru.ncedu.entity.market.Categories;
import ru.ncedu.entity.market.Orders;
import ru.ncedu.entity.market.Providers;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Gamzat on 03.12.2015.
 */

@Stateless
public class UserService {
    public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("NCEDU");
    public static EntityManager em = emf.createEntityManager();
//    @PersistenceUnit(unitName="NCEDU")
//    public static EntityManager em = Persistence.createEntityManagerFactory("NCEDU").createEntityManager();

    public static List<User> getAllUsers() {
        return em.createNamedQuery("User.java.getAllUsers", User.class).getResultList();
    }

    public static List<UserType> getAllTypes(){
        return em.createNamedQuery("UserType.getTypes", UserType.class).getResultList();
    }

    @Nullable
    public static User getUserByName(String userName) {
        TypedQuery<User> query = em.createNamedQuery("User.java.getUserByLogin", User.class);
        query.setParameter("userName", userName);

        User user = null;
            try{
                user = query.getSingleResult();
            }catch (NoResultException ignore){
            }

        if (user == null){
            return null;
        }
        return user;
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

    public Categories addCategories(Categories categories) {
        em.getTransaction().begin();
        Categories result = em.merge(categories);
        em.getTransaction().commit();
        return result;
    }

    public Providers addProviders(Providers providers) {
        em.getTransaction().begin();
        Providers result = em.merge(providers);
        em.getTransaction().commit();
        return result;
    }

    public Orders addOrders(Orders orders){
        em.getTransaction().begin();
        Date date = new Date();
        Orders result = em.merge(orders);
        em.getTransaction().commit();
        return result;
    }

    public static void main(String [] args){

        Categories categories = new Categories("Mobile phones", "Some description");
        em.getTransaction().begin();
        em.persist(categories);
        em.getTransaction().commit();

        Providers providers = new Providers("Philips", "84959963256", "philips@philips.com", "Moscow", "philips.com");
        em.getTransaction().begin();
        em.persist(providers);
        em.getTransaction().commit();

        Date date = new Date();
        Orders orders = new Orders(5,10, date);
        em.getTransaction().begin();
        em.persist(orders);
        em.getTransaction().commit();
    }
}

