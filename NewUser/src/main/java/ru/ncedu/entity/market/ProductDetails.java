package ru.ncedu.entity.market;

import javax.persistence.*;

/**
 * Created by Алёна
 */
@Entity
@Table(name = "ProductDetailsTable")
public class ProductDetails {
    private long goodsId;
    private int amountOfProducts;
    private int pricePerUnit;
    private int amountOfOrders;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "productDetails")
    private Goods goods;

    public ProductDetails(){
    }

    public ProductDetails(int amountOfProducts, int amountOfOrders, int pricePerUnit){
        this.amountOfProducts = amountOfProducts;
        this.amountOfOrders = amountOfOrders;
        this.pricePerUnit= pricePerUnit;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    public long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(long goodsId) {
        this.goodsId = goodsId;
    }

    @Column
    public int getAmountOfProducts() {
        return amountOfProducts;
    }

    public void setAmountOfProducts(int amountOfProducts) {
        this.amountOfProducts = amountOfProducts;
    }

    @Column
    public int getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(int pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    @Column
    public int getAmountOfOrders() {
        return amountOfOrders;
    }

    public void setAmountOfOrders(int amountOfOrders) {
        this.amountOfOrders = amountOfOrders;
    }

    @Override
    public String toString() {
        return "ProductDetails{" +
                "goodsId=" + goodsId +
                ", amountOfProducts=" + amountOfProducts +
                ", pricePerUnit=" + pricePerUnit +
                ", amountOfOrders=" + amountOfOrders +
                '}';
    }
}
