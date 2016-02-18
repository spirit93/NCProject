package ru.ncedu.bean;

import ru.ncedu.entities.Users;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Created by Павел on 17.02.2016.
 */

@ManagedBean
@RequestScoped
public class UserBean {
    String login;
    String password;
    String repassword;
    String email;

    public UserBean(){}

    public UserBean(String login, String password, String repassword, String email) {
        this.login = login;
        this.password = password;
        this.repassword = repassword;
        this.email = email;
    }

    public UserBean(Users users) {
        this.login = users.getUsername();
        this.password = users.getPassword();
        this.repassword = users.getPassword();
        this.email = users.getEmail();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", repassword='" + repassword + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
