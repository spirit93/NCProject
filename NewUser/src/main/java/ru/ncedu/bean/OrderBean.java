package ru.ncedu.bean;

import ru.ncedu.entity.Order;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Алёна
 */
@ManagedBean
@SessionScoped
public class OrderBean implements Serializable {

    String userNameBean;
    Integer orderId;
    Integer number;
    String phone;
    String userNameOrder;
    Integer discount;
    Date date;
    String idOfBt;

    public OrderBean(){
    }

    public OrderBean(Order order){
        this.orderId = order.getOrderId();
        this.phone = order.getPhone();
        this.number = order.getNumber();
        this.discount = order.getDiscount();
        this.date = order.getDate();
        this.userNameOrder = order.getUserNameOrder();
//        this.userBean = new UserBean(order.getUser());
//        this.userNameBean = order.getUser().getUserName();
    }

    public OrderBean(Integer number, String phone, String userNameOrder, Integer discount, Date date){
        this.number = number;
        this.phone = phone;
        this.userNameOrder = userNameOrder;
        this.discount = discount;
        this.date = date;
    }

//    public OrderBean(Integer orderId, String phone){
//        this.orderId = orderId;
//        this.phone = phone;
//    }

    public OrderBean(String phone, String userNameOrder){
        this.phone = phone;
        this.userNameOrder = userNameOrder;
    }

    public OrderBean(Integer orderId, Integer number, String phone, String userNameOrder, Integer discount, Date date){
        this.orderId = orderId;
        this.number = number;
        this.phone = phone;
        this.userNameOrder = userNameOrder;
        this.discount = discount;
        this.date = date;
    }

//    public OrderBean(ru.ncedu.entity.Order order){
//        if(order == null){
//            return;
//        }
//        this.orderId = order.getOrderId();
//        this.number = order.getNumber();
//        this.phone = order.getPhone();
//        this.discount = order.getDiscount();
//        this.date = order.getDate();
//    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }


//    public UserBean getUserBean() {
//        return userBean;
//    }
//
//    public void setUserBean(UserBean userBean) {
//        this.userBean = userBean;
//    }

    public String getUserNameBean() {
        return userNameBean;
    }

    public void setUserNameBean(String userNameBean) {
        this.userNameBean = userNameBean;
    }

    public String getIdOfBt() {
        return idOfBt;
    }

    public void setIdOfBt(String idOfBt) {
        this.idOfBt = idOfBt;
    }

    //
//    public Integer getId() {
//        return Id;
//    }
//
//    public void setId(Integer id) {
//        Id = id;
//    }

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

//    @Override
//    public boolean equals(Object o){
//        if (this == o) return true;
//        if (!(o instanceof OrderBean)) return false;
//        OrderBean orders = (OrderBean) o;
//        return  Objects.equals(orderId, orders.orderId) &&
//                Objects.equals(number, orders.number) &&
//                Objects.equals(phone, orders.phone) &&
//                Objects.equals(discount, orders.discount) &&
//                Objects.equals(date, orders.date);
//    }
//
//    @Override
//    public int hashCode() {
//        int result = (int) (orderId ^ (orderId >>> 32));
//        result = 31 * result + phone.hashCode();
//        result = 31 * result + number;
//        result = 31 * result + discount;
//        result = 31 * result + date.hashCode();
//        return result;
//    }
}

