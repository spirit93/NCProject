package ru.ncedu.entity;

import javax.persistence.*;

/**
 * Created by Алёна on 17.03.2016.
 */
@Entity
@Table(name = "ProductsTable")
@NamedQueries({
//        @NamedQuery(name = "Product.getProductByName",query = "select pr from productstable pr where pr.nameOfProduct =: nameOfProduct")
})
public class Products {

    private Categories categories;
    private Providers providers;
    private long productsId;
    private String nameOfProduct;
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "providerId")
    public Providers getProviders(){
            return providers;
        }

    public void setProviders(Providers providers){
            this.providers = providers;
        }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "productsId")
    private ProductDetails productDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId")
    public Categories getCategories(){
            return categories;
        }

    public  void setCategories(Categories categories){
            this.categories = categories;
        }

    public Products(){}

    public Products(String nameOfProduct, int amountOfProducts, int pricePerOne, int amountOfOrders, String image){
        this.nameOfProduct = nameOfProduct;
        this.image = image;
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

