package ru.ncedu.entities;



import javax.persistence.*;
import javax.persistence.GeneratedValue;
import java.io.Serializable;

@Entity
@Table
public class UsersType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @Column
    private String typeOfUser;

    public UsersType(){}

    public UsersType(String typeOfUser){
        this.typeOfUser = typeOfUser;
    }

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="id")
    private Users users;

    public Users getUsers(){
        return users;
    }

    public void setUser(Users users) {
        this.users = users;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return typeOfUser;
    }

    public void setType(String typeOfUser) {
        this.typeOfUser = typeOfUser;
    }

}
