package ru.ncedu.bean;

import ru.ncedu.entity.Products;

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
    private CategoriesB category;
    private int providerId;
    private int detaildsId;
    private String categoryName;
    private String providerName;

    public ProductsB(){
    }

    public ProductsB(Products product){
        this.nameOfProduct = product.getNameOfProduct();
        this.category = new CategoriesB(product.getCategories());
        this.categoryName = product.getCategories().getNameOfCategory();
        this.providerName = product.getProviders().getCompanyName();
        this.productsId = product.getProductsId();
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

    public CategoriesB getCategory() {
        return category;
    }

    public void setCategory(CategoriesB category) {
        this.category = category;
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }
}
