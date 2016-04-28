package ru.ncedu.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "users")
@NamedQueries({
        @NamedQuery(name = "User.getAllUsers", query = "SELECT u from users u"),
        @NamedQuery(name = "User.getUserByLogin", query = "SELECT u from users u WHERE u.userName = :userName"),
        @NamedQuery(name = "User.getUserByEmail", query = "SELECT u from users u WHERE u.email = :email")
    }
)

public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    Integer id;

    @Column
    String userName;
    @Column
    String password;
    @Column
    String email;


    @OneToOne
    @JoinColumn(name = "user_type_id")
    private UserType userType;

    public User() {
    }

    public User(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id = " + id+ " /" +
                "userName = " +userName + " /" +
                "email = "+email + " /" +
                "}";
    }
}
