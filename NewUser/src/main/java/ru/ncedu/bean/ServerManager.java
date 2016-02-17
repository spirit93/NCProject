package ru.ncedu.bean;

import ru.ncedu.bean.UserBean;
import ru.ncedu.data.Manager;
import ru.ncedu.entities.Users;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;

@ManagedBean
@ApplicationScoped
public class ServerManager implements Serializable {
    ServerManager(){
    }

    public void addUser(UserBean user) {
//        if (user == null) {
//            return;
//        }
        Manager.register(new Users(user.getLogin(),user.getPassword(),user.getEmail()));
    }
}