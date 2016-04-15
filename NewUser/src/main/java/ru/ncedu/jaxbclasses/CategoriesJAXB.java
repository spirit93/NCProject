package ru.ncedu.jaxbclasses;

import ru.ncedu.entity.Categories;
import ru.ncedu.entity.Products;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by D.Smirnov on 08.04.2016.
 */
@XmlType(propOrder = {"categoryId","nameOfCategory","descriptionOfCategory"})
public class CategoriesJAXB {
    private List<Products> products = new ArrayList<>();
    private long categoryId;
    private String nameOfCategory;
    private String descriptionOfCategory;

    public CategoriesJAXB() {
    }

    public CategoriesJAXB(Categories category) {
        this.categoryId = category.getCategoryId();
        this.nameOfCategory = category.getNameOfCategory();
        this.descriptionOfCategory = category.getDescriptionOfCategory();
    }

    public CategoriesJAXB(String nameOfCategory){
        this.nameOfCategory = nameOfCategory;
    }

    public CategoriesJAXB(String nameOfCategory, String descriptionOfCategory){
        this.nameOfCategory = nameOfCategory;
        this.descriptionOfCategory = descriptionOfCategory;
    }
    //@XmlElement
    public long getCategoryId() {
        return categoryId;
    }

    @XmlElement
    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getNameOfCategory() {
        return nameOfCategory;
    }

    @XmlElement
    public void setNameOfCategory(String nameOfCategory) {
        this.nameOfCategory = nameOfCategory;
    }

    public String getDescriptionOfCategory() {
        return descriptionOfCategory;
    }

    @XmlElement
    public void setDescriptionOfCategory(String descriptionOfCategory) {
        this.descriptionOfCategory = descriptionOfCategory;
    }

    @Override
    public String toString() {
        return "CategoriesJAXB{" +
                "categoryId=" + categoryId +
                ", nameOfCategory='" + nameOfCategory + '\'' +
                ", descriptionOfCategory='" + descriptionOfCategory + '\'' +
                '}';
    }
}
