package ru.ncedu.bean;

import javax.annotation.ManagedBean;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import javax.enterprise.context.ApplicationScoped;
import java.util.concurrent.TimeUnit;

/**
 * Created by Павел on 28.04.2016.
 */

@Stateful
//@StatefulTimeout(value = 10, unit = TimeUnit.MINUTES)
public class UserStatus {
    static User a ;

    public UserStatus(User b){
        a = b;
    }

//    public void getStatusOfUser(User user){
//
//    }

    public UserStatus() {
        a = null;
    }

    public static User getUser(){
        return a;
    }

    public static void setStatus(User status) {
        a = status;
    }
}
