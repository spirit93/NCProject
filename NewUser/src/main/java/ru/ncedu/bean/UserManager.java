package ru.ncedu.bean;

import ru.ncedu.ejb.regCheckEjb;
import ru.ncedu.entity.User;
import ru.ncedu.service.UserService;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gamzat on 03.12.2015.
 */
//@ManagedBean
//@ApplicationScoped
@Stateless
public class UserManager implements Serializable {

    @Inject
    regCheckEjb reg;

    public UserManager() {
    }

    public ru.ncedu.bean.User getUserByUserName( String userName){
        User us = UserService.getUserByName(userName);
        if (us != null){
            return new ru.ncedu.bean.User(us);
        }
        return null;
    }

    public List<ru.ncedu.bean.User> getAllUsers(){
        List<ru.ncedu.entity.User> users = UserService.getAllUsers();

        List<ru.ncedu.bean.User> resultList= new ArrayList<ru.ncedu.bean.User>(users.size());
        for (ru.ncedu.entity.User user: users){
            resultList.add(new ru.ncedu.bean.User(user));
        }

        return resultList;
    }

    public List<ru.ncedu.bean.UserType> getAllUserTypes(){
        List<ru.ncedu.entity.UserType> userTypes = UserService.getAllTypes();

        List<ru.ncedu.bean.UserType> resultListUT= new ArrayList<UserType>(userTypes.size());
        for (ru.ncedu.entity.UserType userType: userTypes){
            resultListUT.add(new ru.ncedu.bean.UserType(userType));
        }

        return resultListUT;
    }

    public void addUser(ru.ncedu.bean.User user) {

        UserService.addUser(new User(user.getUserName(),user.getPassword(),user.getEmail()));
        UserService.addUserType(new ru.ncedu.entity.UserType(0));

//    if (reg.regUser(reg.getUser())){
//
//        }
    }

    public void addTestUser() {
        UserService.addUser(new User("name","itIsPas","itIsMail"));
    }


}