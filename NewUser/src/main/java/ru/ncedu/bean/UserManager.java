package ru.ncedu.bean;

import ru.ncedu.entity.User;
import ru.ncedu.service.UserService;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gamzat on 03.12.2015.
 */
@ManagedBean
@ApplicationScoped
public class UserManager implements Serializable {

    public UserManager() {
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
        if (user == null) {
            return;
        }
        UserService.addUser(new User(user.getUserName(),user.getPassword(),user.getEmail()));
        UserService.addUserType(new ru.ncedu.entity.UserType(0));
    }

}