package ru.ncedu.jaxbclasses;

import ru.ncedu.entity.Products;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by D.Smirnov on 08.04.2016.
 */
@XmlType(propOrder = {"productsId","nameOfProduct","categoryId", "productsDetailsId", "providerId", "categoryName", "providerName"})
public class ProductsJAXB {
    private long productsId;
    private String nameOfProduct;
    private long categoryId;
    private long productsDetailsId;
    private long providerId;
    private String categoryName;
    private String providerName;

    public ProductsJAXB() {}

    public ProductsJAXB(Products products){
        this.productsId = products.getProductsId();
        this.nameOfProduct = products.getNameOfProduct();
        this.categoryId = products.getCategories().getCategoryId();
        this.productsDetailsId = products.getProductDetails().getProductsDetailsId();
        this.providerId = products.getProviders().getProviderId();
        this.categoryName = products.getCategories().getNameOfCategory();
        this.providerName = products.getProviders().getCompanyName();
    }

    @XmlElement
    public void setProductsId(long productsId) {
        this.productsId = productsId ;
    }

    public long getProductsId() {
        return productsId;
    }

    @XmlElement
    public void setNameOfProduct(String nameOfProduct) {
        this.nameOfProduct = nameOfProduct;
    }

    public String getNameOfProduct() {
        return nameOfProduct;
    }

    @XmlElement
    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId ;
    }

    public long getCategoryId() {
        return categoryId;
    }

    @XmlElement
    public void setProductsDetailsId(long productsDetailsId) {
        this.productsDetailsId = productsDetailsId ;
    }

    public long getProductsDetailsId() {
        return productsDetailsId;
    }

    @XmlElement
    public void setProviderId(long providerId) {
        this.providerId = providerId ;
    }

    public long getProviderId() { return providerId; }

    @XmlElement
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }

    public String getCategoryName() { return categoryName; }

    @XmlElement
    public void setProviderName(String providerName) { this.providerName = providerName; }

    public String getProviderName() { return providerName; }

}
