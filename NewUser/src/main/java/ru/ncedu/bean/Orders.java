package ru.ncedu.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.Date;
import java.util.Objects;

/**
 * Created by Алёна
 */
@ManagedBean
@RequestScoped
public class Orders {

    private long orderId;
    private int numberOfOrders;
    private int discount;
    private Date date;

    public Orders(){
    }

    public Orders(int numberOfOrders, int discount, Date date){
        this.numberOfOrders = numberOfOrders;
        this.discount = discount;
        this.date= date;
    }

    public Orders(long orderId, int numberOfOrders, int discount, Date date){
        this.orderId = orderId;
        this.numberOfOrders = numberOfOrders;
        this.discount = discount;
        this.date= date;
    }

    public Orders(ru.ncedu.entity.Orders orders){
        if(orders == null){
            return;
        }
        this.orderId = orders.getOrderId();
        this.numberOfOrders = orders.getNumberOfOrders();
        this.discount = orders.getDiscount();
        this.date = orders.getDate();
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public int getNumberOfOrders() {
        return numberOfOrders;
    }

    public void setNumberOfOrders(int numberOfOrders) {
        this.numberOfOrders = numberOfOrders;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orderId=" + orderId +
                ", numberOfOrders=" + numberOfOrders +
                ", discount=" + discount +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof Orders)) return false;
        Orders orders = (Orders) o;
        return  Objects.equals(orderId, orders.orderId) &&
                Objects.equals(numberOfOrders, orders.numberOfOrders) &&
                Objects.equals(discount, orders.discount) &&
                Objects.equals(date, orders.date);
    }

    @Override
    public int hashCode() {
        int result = (int) (orderId ^ (orderId >>> 32));
        result = 31 * result + numberOfOrders;
        result = 31 * result + discount;
        result = 31 * result + date.hashCode();
        return result;
    }
}

