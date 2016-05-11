package ru.ncedu.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Павел on 13.02.2016.
 */

@Entity(name = "usertypes")
@NamedQueries({
    @NamedQuery(name = "UserType.getTypes", query = "select  u from usertypes u")
})
public class UserType implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "user_type_id")
    int id;

    @Column
    int type;               // 0 - user, 1 - admin ;

    @OneToOne(mappedBy = "userType")
    private User users;

    public UserType(){
    }

    public UserType(Integer id, int type){
        this.id = id;
        this.type = type;
    }

    public UserType(int type) {
        this.type = type;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "UserType{" +
                "id=" + id +
                ", type=" + type +
                '}';
    }
}
