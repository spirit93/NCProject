package ru.ncedu.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Алёна
 */

@Entity
@NamedQueries({
        @NamedQuery(name = "ProductDetails.getAllProductDetails", query = "SELECT pd from productdetailstable pd"),
        @NamedQuery(name = "ProductDetails.getProductDetailsById", query = "SELECT pd from productdetailstable pd WHERE pd.productsId = :productsId")
}
)
@Table(name = "ProductDetailsTable")
public class ProductDetails implements Serializable {
    private long productsId;
    private int amountOfProducts;
    private int pricePerUnit;
    private int amountOfOrders;

    @OneToOne(mappedBy = "productDetails")
    private Products products;

    public ProductDetails(){
    }

    public ProductDetails(int amountOfProducts, int amountOfOrders, int pricePerUnit){
        this.amountOfProducts = amountOfProducts;
        this.amountOfOrders = amountOfOrders;
        this.pricePerUnit= pricePerUnit;
    }

    @Id
    @Column(name = "productdetailsid")
    public long getProductsId() {
        return productsId;
    }

    public void setProductsId(long productsId) {
        this.productsId = productsId;
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
                "productsId=" + productsId +
                ", amountOfProducts=" + amountOfProducts +
                ", pricePerUnit=" + pricePerUnit +
                ", amountOfOrders=" + amountOfOrders +
                '}';
    }
}