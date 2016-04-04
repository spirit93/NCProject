package ru.ncedu.entity;

import javax.persistence.*;

/**
 * Created by Алёна on 17.03.2016.
 */
@Entity(name = "ProductsTable")
@NamedQueries({
//        @NamedQuery(name = "Product.getProductByName",query = "select pr from productstable pr where pr.nameOfProduct =: nameOfProduct")
})
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long productsId;

    @Column
    private String nameOfProduct;

    @Column
    private String image;

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

    public Products(String nameOfProduct, int amountOfProducts, int pricePerOne, int amountOfOrders, String image){
        this.nameOfProduct = nameOfProduct;
        this.image = image;
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

    public void setImage(String image) {
            this.image = image;
        }


    public long getProductsId() {
        return productsId;
    }

    public String getNameOfProduct() {
            return nameOfProduct;
        }

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


