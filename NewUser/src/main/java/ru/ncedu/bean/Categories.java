package ru.ncedu.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.Objects;

/**
 * Created by Алёна
 */
@ManagedBean
@RequestScoped
public class Categories {

    private long categoryId;
    private String nameOfCategory;
    private String descriptionOfCategory;

    public Categories(){
    }

    public Categories(String nameOfCategory, String descriptionOfCategory){
        this.nameOfCategory = nameOfCategory;
        this.descriptionOfCategory = descriptionOfCategory;
    }

    public Categories(long categoryId, String nameOfCategory, String descriptionOfCategory){
        this.categoryId = categoryId;
        this.nameOfCategory = nameOfCategory;
        this.descriptionOfCategory = descriptionOfCategory;
    }

    public Categories(ru.ncedu.entity.Categories categories){
        if(categories == null){
            return;
        }
        this.categoryId = categories.getCategoryId();
        this.nameOfCategory = categories.getNameOfCategory();
        this.descriptionOfCategory = categories.getDescriptionOfCategory();
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

    @Override
    public String toString() {
        return "Categories{" +
                "categoryId=" + categoryId +
                ", nameOfCategory='" + nameOfCategory + '\'' +
                ", descriptionOfCategory='" + descriptionOfCategory + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categories categories = (Categories) o;
        return  Objects.equals(categoryId, categories.categoryId) &&
                Objects.equals(nameOfCategory, categories.nameOfCategory) &&
                Objects.equals(descriptionOfCategory, categories.descriptionOfCategory);
    }
    @Override
    public int hashCode() {
        return Objects.hash(categoryId, nameOfCategory, descriptionOfCategory);
    }
}
