package ru.ncedu.jaxbclasses;

import ru.ncedu.entity.Products;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by D.Smirnov on 08.04.2016.
 */
@XmlType(propOrder = {"productsId","nameOfProduct","categoryId", "productsDetailsId", "providerId"})
public class ProductsJAXB {
    private long productsId;
    private String nameOfProduct;
    private long categoryId;
    private long productsDetailsId;
    private long providerId;

    public ProductsJAXB() {}

    public ProductsJAXB(Products products){
        this.productsId = products.getProductsId();
        this.nameOfProduct = products.getNameOfProduct();
        this.categoryId = products.getCategories().getCategoryId();
        this.productsDetailsId = products.getProductDetails().getProductsDetailsId();
        this.providerId = products.getProviders().getProviderId();
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
}
