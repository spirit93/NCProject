package ru.ncedu.entity;

import ru.ncedu.bean.ProdDetailsB;

import javax.persistence.*;

/**
 * Created by Алёна on 17.03.2016.
 */
@Entity(name = "ProductDetailsTable")
//@Table
public class ProductDetails {
    private long productsDetailsId;
    private int amountOfProducts;
    private int pricePerUnit;
    private int amountOfOrders;

//    @OneToOne(cascade = CascadeType.ALL, mappedBy = "productDetails")
    @OneToOne(mappedBy = "productDetails")
    private Products ProductsTable;

    public ProductDetails(){
    }

    public ProductDetails(ProdDetailsB detailsB){
        this.amountOfProducts = detailsB.getAmountOfProducts();
        this.amountOfOrders = detailsB.getAmountOfOrders();
        this.pricePerUnit= detailsB.getPricePerUnit();
    }

    public ProductDetails(int amountOfProducts, int amountOfOrders, int pricePerUnit){
        this.amountOfProducts = amountOfProducts;
        this.amountOfOrders = amountOfOrders;
        this.pricePerUnit= pricePerUnit;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    public long getProductsDetailsId() {
        return productsDetailsId;
    }

    public void setProductsDetailsId(long productsId) {
        this.productsDetailsId = productsId;
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
                "productsId=" + productsDetailsId +
                ", amountOfProducts=" + amountOfProducts +
                ", pricePerUnit=" + pricePerUnit +
                ", amountOfOrders=" + amountOfOrders +
                '}';
    }
}
