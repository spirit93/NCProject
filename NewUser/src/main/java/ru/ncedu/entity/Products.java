package ru.ncedu.entity;

import ru.ncedu.jaxbclasses.ProductsJAXB;
import ru.ncedu.service.MarketService;

import javax.persistence.*;

/**
 * Created by Алёна on 17.03.2016.
 */
@Entity(name = "ProductsTable")
@NamedQueries({
        @NamedQuery(name = "Products.getAllProducts", query = "SELECT pr FROM ProductsTable pr"),
        @NamedQuery(name = "Products.getProductById", query = "SELECT pr FROM ProductsTable pr WHERE pr.productsId=:productsId"),
//        @NamedQuery(name = "Product.getProductByName",query = "select pr from ProductsTable pr where pr.nameOfProduct =: nameOfProduct")
})
public class Products {
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


    public Products(ProductsJAXB productsJAXB){
        Categories category = (productsJAXB.getCategoryName() == null) ? null
                : MarketService.getCategoryByName(productsJAXB.getCategoryName());
        Providers provider = (productsJAXB.getProviderName() == null) ? null
                : MarketService.getProviderByName(productsJAXB.getProviderName());
        this.productsId = productsJAXB.getProductsId();
        this.nameOfProduct = productsJAXB.getNameOfProduct();
        this.providers = provider;
        this.categories = category;
    }

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


