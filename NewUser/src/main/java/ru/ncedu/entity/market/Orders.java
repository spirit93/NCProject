package ru.ncedu.entity.market;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Алёна
 */
@Entity
@Table(name = "OrdersTable")
public class Orders {

    private long orderId;
    private long goodsId;
    private long recipientId;
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
    public long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(long goodsId) {
        this.goodsId = goodsId;
    }

    @Column
    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Column
    public long getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(long recipientId) {
        this.recipientId = recipientId;
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
                ", goodsId=" + goodsId +
                ", numberOfOrders=" + numberOfOrders +
                ", discount=" + discount +
                ", recipientId=" + recipientId +
                ", date='" + date + '\'' +
                '}';
    }
}
