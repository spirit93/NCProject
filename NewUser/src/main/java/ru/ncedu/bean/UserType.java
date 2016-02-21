package ru.ncedu.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Created by Gamzat on 03.12.2015.
 */
@ManagedBean
@RequestScoped
public class UserType {
    Integer id;
    Integer type;           // 0 -user , 1 - admin;

    public UserType() {
    }

    public UserType(Integer type) {
        this.type = type;
    }

    public UserType(ru.ncedu.entity.UserType userType) {
        this.id = userType.getId();
        this.type = userType.getType();
    }

    public UserType(Integer id, Integer type) {
        this.id = id;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
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
