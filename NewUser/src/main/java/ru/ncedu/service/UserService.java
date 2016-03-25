package ru.ncedu.service;

//import com.sun.istack.internal.Nullable;
import ru.ncedu.entity.*;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;

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

   // @Nullable
    public static User getUserByName(String userName) {
        TypedQuery<User> query = em.createNamedQuery("User.getUserByLogin", User.class);
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
        UserType userType = em.merge(new UserType());
        user.setUserType(userType);
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

    public static Products addProducts(Products products) {
        em.getTransaction().begin();
        Categories categories = em.merge(new Categories());
        products.setCategories(categories);
        Providers providers = em.merge(new Providers());
        products.setProviders(providers);
        Products result = em.merge(products);
        em.getTransaction().commit();
        return result;
    }

    public static Categories addCategories(Categories categories) {
        em.getTransaction().begin();
        Categories result = em.merge(categories);
        em.getTransaction().commit();
        return result;
    }

    public static Providers addProviders(Providers providers) {
        em.getTransaction().begin();
        Providers result = em.merge(providers);
        em.getTransaction().commit();
        return result;
    }

    public static List<Products> getAllProducts(){
            return em.createNamedQuery("Products.getAllProducts", Products.class).getResultList();
    }

    public static List<Providers> getAllProviders(){
        return em.createNamedQuery("Providers.getAllProviders", Providers.class).getResultList();
    }

    public static void removeProviders(Providers providers) {
        em.getTransaction().begin();
        em.remove(providers);
        em.getTransaction().commit();
    }

    public static void removeProducts(Products products) {
        em.getTransaction().begin();
        em.remove(products);
        em.getTransaction().commit();
    }

    public static Providers getProvidersByCompanyName(String companyName) {
        TypedQuery<Providers> query = em.createNamedQuery("Providers.getProvidersByCompanyName", Providers.class);
        query.setParameter("companyName", companyName);

        Providers providers = null;
        try{
            providers = query.getSingleResult();
        }catch (NoResultException ignore){
        }

        if (providers == null){
            return null;
        }
        return providers;
    }

    public static void removeCategories(Categories categories) {
        em.getTransaction().begin();
        em.remove(categories);
        em.getTransaction().commit();
    }

    public static List<ru.ncedu.entity.Categories> getAllCategories(){
        return em.createNamedQuery("Categories.getAllCategories", ru.ncedu.entity.Categories.class).getResultList();
    }

    public static Categories getCategoryByName(String nameOfCategory) {
        TypedQuery<Categories> query = em.createNamedQuery("Categories.getCategoryByName", Categories.class);
        query.setParameter("nameOfCategory", nameOfCategory);

        Categories categories = null;
        try{
            categories = query.getSingleResult();
        }catch (NoResultException ignore){
        }

        if (categories == null){
            return null;
        }
        return categories;
    }

    public static List<Orders> getAllOrders() {
        return em.createNamedQuery("Orders.getAllOrders", Orders.class).getResultList();
    }

    public static Orders addOrders(Orders orders){
        em.getTransaction().begin();
        Orders result = em.merge(orders);
        em.getTransaction().commit();
        return result;
    }


}


