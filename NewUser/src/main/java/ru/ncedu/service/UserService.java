package ru.ncedu.service;

//import com.sun.istack.internal.Nullable;
import org.apache.commons.codec.digest.DigestUtils;
import ru.ncedu.entity.Products;
import ru.ncedu.entity.Providers;
import ru.ncedu.entity.User;
import ru.ncedu.entity.UserType;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;

@Stateless
public class UserService  extends Service{

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

    public static User getUserByEmail(String email) {
        TypedQuery<User> query = em.createNamedQuery("User.getUserByEmail", User.class);
        query.setParameter("email", email);

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
        String pasMD5 = DigestUtils.md5Hex(user.getPassword());
        user.setPassword(pasMD5);

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

}
