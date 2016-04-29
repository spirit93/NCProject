package ru.ncedu.bean;

import ru.ncedu.entity.Categories;
import ru.ncedu.entity.Products;
import ru.ncedu.service.MarketService;

import java.util.List;

/**
 * Created by Павел on 23.03.2016.
 */
public class CategoriesB {
    private List<Products> products;
    private long categoryId;
    private String nameOfCategory;
    private String descriptionOfCategory;

    public CategoriesB() {
    }

    public CategoriesB(Categories category) {
        this.categoryId = category.getCategoryId();
        this.nameOfCategory = category.getNameOfCategory();
        this.descriptionOfCategory = category.getDescriptionOfCategory();
    }

    public CategoriesB(String nameOfCategory, String descriptionOfCategory) {
        this.nameOfCategory = nameOfCategory;
        this.descriptionOfCategory = descriptionOfCategory;
    }

    public List<Products> getProducts() {
        return products;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getNameOfCategory() {
        return nameOfCategory;
    }

    public void setNameOfCategory(String nameOfCategory) {
        this.nameOfCategory = nameOfCategory;
    }

    public String getDescriptionOfCategory() {
        return descriptionOfCategory;
    }

    public void setDescriptionOfCategory(String descriptionOfCategory) {
        this.descriptionOfCategory = descriptionOfCategory;
    }
}
