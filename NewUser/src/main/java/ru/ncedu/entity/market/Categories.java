package ru.ncedu.entity.market;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Алёна
 */

@Entity
@Table(name = "CategoriesTable")
public class Categories {
    private List<Products> products; //private List<Goods> goods = new ArrayList<>();
    private long categoryId;
    private String nameOfCategory;
    private String descriptionOfCategory;

    @OneToMany(mappedBy = "categories",fetch = FetchType.LAZY)
    public List<Products> getProducts(){
        return products;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
    }

    public Categories(){}

    public Categories(String nameOfCategory){
        this.nameOfCategory = nameOfCategory;
    }

    public Categories(String nameOfCategory, String descriptionOfCategory){
        this.nameOfCategory = nameOfCategory;
        this.descriptionOfCategory = descriptionOfCategory;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    @Column
    public String getNameOfCategory() {
        return nameOfCategory;
    }

    public void setNameOfCategory(String nameOfCategory) {
        this.nameOfCategory = nameOfCategory;
    }

    @Column
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
}