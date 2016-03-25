package ru.ncedu.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Created by Алёна
 */
@ManagedBean
@RequestScoped
public class Products {

    private Categories categories;
    private Providers providers;
    private String nameOfProduct;
    private String image;

    public Products(){
    }

    public Products(Categories categories, Providers providers, String nameOfProduct, String image){
        this.categories = categories;
        this.providers = providers;
        this.nameOfProduct = nameOfProduct;
        this.image = image;
    }

    public Products (ru.ncedu.entity.Products products){
        this.categories = new Categories(products.getCategories());
        this.providers = new Providers(products.getProviders());
        this.nameOfProduct = products.getNameOfProduct();
        this.image = products.getImage();
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public Providers getProviders() {
        return providers;
    }

    public void setProviders(Providers providers) {
        this.providers = providers;
    }

    public String getNameOfProduct() {
        return nameOfProduct;
    }

    public void setNameOfProduct(String nameOfProduct) {
        this.nameOfProduct = nameOfProduct;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Products{" +
                "categories=" + categories +
                ", providers=" + providers +
                ", nameOfProduct='" + nameOfProduct + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

}
