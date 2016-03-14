package ru.ncedu.entity.market;

import javax.persistence.*;
import java.util.List;
/**
 * Created by Алёна
 */
@Entity
@Table(name = "GoodsTable")
public class Goods {
    private Categories categories;
    private Providers providers;
    private long goodsId;
    private long providerId;
    private long categoryId;
    private String nameOfProduct;
    private String image;

    @ManyToOne
    @JoinColumn(name = "providerId")
    public Providers getProviders(){
        return providers;
    }

    public void setProviders(Providers providers){
        this.providers = providers;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "goodsId")
    private ProductDetails productDetails;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    public Categories getCategories(){
        return categories;
    }

    public  void setCategories(Categories categories){
        this.categories = categories;
    }


//    @ManyToOne
//    @JoinColumn(name = "goodsId")
//    private Orders orders;

    public Goods(){}

    public Goods(String nameOfProduct, int amountOfProducts, int pricePerOne, int amountOfOrders, String image){
        this.nameOfProduct = nameOfProduct;
        this.image = image;
    }

    public void setGoodsId(long goodsId) {
        this.goodsId = goodsId ;
    }

    public void setNameOfProduct(String nameOfProduct) {
        this.nameOfProduct = nameOfProduct;
    }

    public void setProviderId(long providerId) {
        this.providerId = providerId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getGoodsId() {

        return goodsId;
    }

    @Column
    public String getNameOfProduct() {
        return nameOfProduct;
    }

    @Column
    public long getProviderId() {
        return providerId;
    }

    @Column
    public long getCategoryId() {
        return categoryId;
    }

    @Column
    public String getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "goodsId=" + goodsId +
                ", nameOfProduct='" + nameOfProduct + '\'' +
                ", providerId=" + providerId +
                ", categoryId=" + categoryId +
                ", image='" + image + '\'' +
                '}';
    }
}
