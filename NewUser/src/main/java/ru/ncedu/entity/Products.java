package ru.ncedu.entity;

import javax.persistence.*;

/**
 * Created by Алёна on 17.03.2016.
 */
@Entity(name = "ProductsTable")
//@Table(name = "ProductsTable")
@NamedQueries({
//        @NamedQuery(name = "Product.getProductByName",query = "select pr from productstable pr where pr.nameOfProduct =: nameOfProduct")
})
public class Products {
    private Categories categories;
    private Providers providers;
    private long productsId;
    private String nameOfProduct;
    private String image;

//    @OneToOne(cascade = CascadeType.ALL)
    @OneToOne
    @JoinColumn(name = "productsDetailsId")
    private ProductDetails productDetails;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "providerId")
    public Providers getProviders(){
            return providers;
        }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId")
    public Categories getCategories(){
            return categories;
        }

    public Products(){}

    public Products(String nameOfProduct,Categories categories){
        this.nameOfProduct = nameOfProduct;
        this.categories = categories;
    }

    public Products(String nameOfProduct, int amountOfProducts, int pricePerOne, int amountOfOrders, String image){
        this.nameOfProduct = nameOfProduct;
        this.image = image;
    }

    public void setProductDetails(ProductDetails productDetails) {
        this.productDetails = productDetails;
    }

//    public ProductDetails getProductDetails() {
//        return productDetails;
//    }

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

    public void setImage(String image) {
            this.image = image;
        }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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


