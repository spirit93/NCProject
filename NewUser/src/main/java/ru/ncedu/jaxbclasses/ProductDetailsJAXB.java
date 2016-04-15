package ru.ncedu.jaxbclasses;

import ru.ncedu.entity.ProductDetails;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by D.Smirnov on 08.04.2016.
 */
@XmlType(propOrder = {"productsDetailsId","amountOfProducts","pricePerUnit","amountOfOrders", "pathToImg"})
public class ProductDetailsJAXB {
    private long productsDetailsId;
    private int amountOfProducts;
    private int pricePerUnit;
    private int amountOfOrders;
    private String pathToImg;

    public ProductDetailsJAXB(){}

    public ProductDetailsJAXB(ProductDetails productDetails){
        this.productsDetailsId = productDetails.getProductsDetailsId();
        this.amountOfProducts = productDetails.getAmountOfProducts();
        this.amountOfOrders = productDetails.getAmountOfOrders();
        this.pricePerUnit= productDetails.getPricePerUnit();
        this.pathToImg = productDetails.getPathToImg();
    }

    public long getProductsDetailsId() {
        return productsDetailsId;
    }

    @XmlElement
    public void setProductsDetailsId(long productsId) {
        this.productsDetailsId = productsId;
    }

    public String getPathToImg() {
        return pathToImg;
    }

    @XmlElement
    public void setPathToImg(String pathToImg) {
        this.pathToImg = pathToImg;
    }

    public int getAmountOfProducts() {
        return amountOfProducts;
    }

    @XmlElement
    public void setAmountOfProducts(int amountOfProducts) {
        this.amountOfProducts = amountOfProducts;
    }

    public int getPricePerUnit() {
        return pricePerUnit;
    }

    @XmlElement
    public void setPricePerUnit(int pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public int getAmountOfOrders() {
        return amountOfOrders;
    }

    @XmlElement
    public void setAmountOfOrders(int amountOfOrders) {
        this.amountOfOrders = amountOfOrders;
    }

}
