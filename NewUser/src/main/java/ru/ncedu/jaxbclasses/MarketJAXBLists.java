package ru.ncedu.jaxbclasses;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dan Smirnov on 08.04.2016.
 */
@XmlRootElement(name = "database")
public class MarketJAXBLists {
    private List<CategoriesJAXB> categoryList = new ArrayList<>();
    private List<ProductDetailsJAXB> productDetailsList = new ArrayList<>();
    private List<ProductsJAXB> productsList = new ArrayList<>();
    private List<ProvidersJAXB> providersList = new ArrayList<>();

    public MarketJAXBLists() {}

    public MarketJAXBLists (List<CategoriesJAXB> aCategoryList, List<ProductDetailsJAXB> aProductDetailsList, List<ProductsJAXB> aProductsList, List<ProvidersJAXB> aProvidersList){
        this.categoryList = aCategoryList;
        this.providersList = aProvidersList;
        this.productsList = aProductsList;
        this.productDetailsList = aProductDetailsList;
    }

    public List<CategoriesJAXB> getCategoryList(){
        return this.categoryList;
    }

    @XmlElement(name="category")
    @XmlElementWrapper(name="allCategories")
    public  void setCategoryList(List<CategoriesJAXB> aCategoryList){
        this.categoryList = aCategoryList;
    }

    public List<ProvidersJAXB> getProvidersList(){
        return this.providersList;
    }

    @XmlElement(name = "provider")
    @XmlElementWrapper(name = "allProviders")
    public void setProvidersList(List<ProvidersJAXB> aProvidersList){
        this.providersList = aProvidersList;
    }

    public List<ProductsJAXB> getProductsList(){
        return this.productsList;
    }

    @XmlElement(name = "product")
    @XmlElementWrapper(name = "allProducts")
    public void setProductsList (List<ProductsJAXB> aProductsList){
        this.productsList = aProductsList;
    }

    public List <ProductDetailsJAXB> getProductDetailsList(){
        return this.productDetailsList;
    }

    @XmlElement(name = "productDetails")
    @XmlElementWrapper(name = "allProductDetails")
    public void setProductDetailsList (List<ProductDetailsJAXB> aProductDetailsList) {
        this.productDetailsList = aProductDetailsList;
    }
}
