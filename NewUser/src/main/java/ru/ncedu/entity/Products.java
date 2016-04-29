package ru.ncedu.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Алёна on 17.03.2016.
 */
@Entity(name = "ProductsTable")
@NamedQueries({
        @NamedQuery(name = "Products.getAllProducts", query = "SELECT pr FROM ProductsTable pr"),
//        @NamedQuery(name = "Products.getProdById",query = "select p from ProductsTable p where p.productsId =: productsId")
    }
 )
public class Products implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long productsId;

    @Column
    private String nameOfProduct;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId")
    private Categories categories;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "providerId")
    private Providers providers;

//    @OneToOne(cascade = CascadeType.ALL)
    @OneToOne
    @JoinColumn(name = "productsDetailsId")
    private ProductDetails productDetails;


    public Providers getProviders(){
            return providers;
        }


    public Categories getCategories(){
            return categories;
        }

    public Products(){}

    public Products(String nameOfProduct,Categories categories,Providers providers){
        this.providers = providers;
        this.nameOfProduct = nameOfProduct;
        this.categories = categories;
    }

    public Products(String nameOfProduct, int amountOfProducts, int pricePerOne, int amountOfOrders){
        this.nameOfProduct = nameOfProduct;
    }

    public void setProductDetails(ProductDetails productDetails) {
        this.productDetails = productDetails;
    }

    public ProductDetails getProductDetails() {
        return productDetails;
    }

    public void setProviders(Providers providers){
        this.providers = providers;
    }

    public  void setCategories(Categories categories){
            this.categories = categories;
        }

    public void setProductsId(long productsId) {
            this.productsId = productsId ;
        }

    public void setNameOfProduct(String nameOfProduct) {
            this.nameOfProduct = nameOfProduct;
        }

    public long getProductsId() {
        return productsId;
    }

    public String getNameOfProduct() {
            return nameOfProduct;
        }

    @Override
    public String toString() {
        return "Products{" +
                "productsId=" + productsId +
                ", nameOfProduct='" + nameOfProduct + '\'' +
                '}';
    }
}


