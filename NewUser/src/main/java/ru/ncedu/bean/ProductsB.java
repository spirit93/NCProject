package ru.ncedu.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Created by Павел on 19.03.2016.
 */

@ManagedBean
@RequestScoped
public class ProductsB {
    private long productsId;
    private String nameOfProduct;
    private int categoryId;
    private int providerId;
    private int detaildsId;

    public ProductsB(){
    }

    public long getProductsId() {
        return productsId;
    }

    public void setProductsId(long productsId) {
        this.productsId = productsId;
    }

    public String getNameOfProduct() {
        return nameOfProduct;
    }

    public void setNameOfProduct(String nameOfProduct) {
        this.nameOfProduct = nameOfProduct;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }

    public int getDetaildsId() {
        return detaildsId;
    }

    public void setDetaildsId(int detaildsId) {
        this.detaildsId = detaildsId;
    }
}
