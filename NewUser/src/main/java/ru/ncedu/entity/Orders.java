package ru.ncedu.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Алёна on 17.03.2016.
 */
@Entity(name = "OrdersTable")
//@Table
public class Orders {

    private long orderId;
    private int numberOfOrders;
    private int discount;
    private Date date;

    public Orders(){}

    public Orders(int numberOfOrders, int discount, Date date){
        this.numberOfOrders = numberOfOrders;
        this.discount = discount;
        this.date = date;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    @Column
    public int getNumberOfOrders() {
        return numberOfOrders;
    }

    public void setNumberOfOrders(int numberOfOrders) {
        this.numberOfOrders = numberOfOrders;
    }

    @Column
    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Column
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orderId=" + orderId +
                ", numberOfOrders=" + numberOfOrders +
                ", discount=" + discount +
                ", date='" + date + '\'' +
                '}';
    }
}
