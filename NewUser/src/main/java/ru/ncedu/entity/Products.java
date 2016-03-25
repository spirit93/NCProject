package ru.ncedu.entity;

import ru.ncedu.entity.Categories;
import ru.ncedu.entity.Providers;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Алёна
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "Products.getAllProducts", query = "SELECT pr from productstable pr"),
        @NamedQuery(name = "Products.getProductsById", query = "SELECT pr from productstable pr WHERE pr.productsId= :productsId")
}
)
@Table(name = "ProductsTable")
public class Products implements Serializable {

    private Categories categories;
    private Providers providers;
    private long productsId;
    private String nameOfProduct;
    private String image;
    private int amountOfProducts;
    private  int pricePerOne;
    private int amountOfOrders;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "providerId")
    public Providers getProviders(){
        return providers;
    }

    public void setProviders(Providers providers){
        this.providers = providers;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "productdetailsid")
    private ru.ncedu.entity.ProductDetails productDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId")
    public Categories getCategories(){
        return categories;
    }

    public  void setCategories(Categories categories){
        this.categories = categories;
    }

    public Products(){}

    public Products(String nameOfProduct, int amountOfProducts, int pricePerOne, int amountOfOrders, String image, Providers providers, Categories categories){
        this.nameOfProduct = nameOfProduct;
        this.amountOfProducts = amountOfProducts;
        this.pricePerOne = pricePerOne;
        this.amountOfOrders = amountOfOrders;
        this.image = image;
        this.providers = providers;
        this.categories = categories;
    }

    public int getAmountOfProducts() {
        return amountOfProducts;
    }

    public void setAmountOfProducts(int amountOfProducts) {
        this.amountOfProducts = amountOfProducts;
    }

    public int getPricePerOne() {
        return pricePerOne;
    }

    public void setPricePerOne(int pricePerOne) {
        this.pricePerOne = pricePerOne;
    }

    public int getAmountOfOrders() {
        return amountOfOrders;
    }

    public void setAmountOfOrders(int amountOfOrders) {
        this.amountOfOrders = amountOfOrders;
    }

    public ProductDetails getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(ProductDetails productDetails) {
        this.productDetails = productDetails;
    }

    public void setProductsId(long productsId) {
        this.productsId = productsId ;
    }

    public void setNameOfProduct(String nameOfProduct) {
        this.nameOfProduct = nameOfProduct;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "productsId")
    public long getProductsId() {
        return productsId;
    }

    @Column
    public String getNameOfProduct() {
        return nameOfProduct;
    }

    @Column
    public String getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "Products{" +
                "productsId=" + productsId +
                ", nameOfProduct='" + nameOfProduct + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}


