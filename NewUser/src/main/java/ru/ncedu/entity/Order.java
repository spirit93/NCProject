package ru.ncedu.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Алёна
 */
@Entity(name = "orders")
@NamedQueries({
       @NamedQuery(name = "Order.getAllOrderedProducts", query = "SELECT p FROM orders o, ProductsTable p WHERE o.status='ordered' and  o.idOfProd = p.productsId"),
       @NamedQuery(name = "Order.getAllOrders", query = "SELECT o FROM orders o WHERE o.status = 'ordered'"),
       @NamedQuery(name = "Order.getOrderById", query = "SELECT o FROM orders o WHERE o.orderId = :orderId")

   //@NamedQuery(name = "Order.getOrderById", query = "SELECT o from orders o WHERE o.orderId = :orderId")
})
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "orderId")
    Integer orderId;
    @Column
    String userNameOrder;
    @Column
    Integer idOfProd;
    @Column
    String phone;
    @Column
    Integer discount;
    @Column
    Integer number;
    @Column
    Date date;
    @Enumerated(EnumType.STRING)
    @Column
    StatusOrd status; // 0 - make an order , 1 - processed order, 2 - canceled order

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public enum StatusOrd{ordered,processed,canceled};

    public Order(){}

    public Order(ru.ncedu.bean.OrderBean orderBean){
        this.status = orderBean.getStatus();
        String toIntId = orderBean.getIdOfBt().substring(7,orderBean.getIdOfBt().length());
        this.idOfProd = Integer.parseInt(toIntId);
        this.userNameOrder = orderBean.getUserNameOrder();
        this.phone = orderBean.getPhone();
        this.discount = orderBean.getDiscount();
        this.number = orderBean.getNumber();
        this.date = orderBean.getDate();
    }

    public Order(String userNameOrder, String phone, Integer discount, Date date, Integer number){
        this.userNameOrder = userNameOrder;
        this.phone = phone;
        this.discount = discount;
        this.date = date;
        this.number = number;
    }

    public Order(String phone, String userNameOrder){
        this.userNameOrder = userNameOrder;
        this.phone = phone;
    }

    public Order(String phone, String userNameOrder, Integer discount, Integer number, Date date, User user){
        this.phone = phone;
        this.userNameOrder = userNameOrder;
        this.discount = discount;
        this.number = number;
        this.date = date;
        this.user = user;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserNameOrder() {
        return userNameOrder;
    }

    public void setUserNameOrder(String userNameOrder) {
        this.userNameOrder = userNameOrder;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getIdOfProd() {
        return idOfProd;
    }

    public void setIdOfProd(Integer idOfProd) {
        this.idOfProd = idOfProd;
    }

    public StatusOrd getStatus() {
        return status;
    }

    public void setStatus(StatusOrd status) {
        this.status =status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", phone='" + phone +
                ", userName='" + userNameOrder +
                ", discount=" + discount +
                ", number=" + number +
                ", date=" + date +
                '}';
    }
}
