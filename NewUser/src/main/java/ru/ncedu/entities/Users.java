package ru.ncedu.entities;

import org.hibernate.usertype.UserType;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table
public class Users implements Serializable {
    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String email;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    public Users(){}

    public Users(String username, String password){
        this.username = username;
        this.password = password;
    }
    public Users(String username, String password, String email){
        this.username = username;
        this.password = password;
        this.email = email;
    }
    @OneToOne(mappedBy="users")
    private UserType userType;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Override
    public String toString(){
        return ("Username " + username + " password " + password + " email " + email);
    }
}
