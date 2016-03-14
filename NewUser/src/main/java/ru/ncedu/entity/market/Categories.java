package ru.ncedu.entity.market;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Алёна
 */
@Entity
@Table(name = "CategoriesTable")
public class Categories {
    private List<Goods> goods = new ArrayList(); //private List<Goods> goods = new ArrayList<>();
    private long categoryId;
    private String nameOfCategory;
    private String descriptionOfCategorye;

    @OneToMany(mappedBy = "goods",fetch = FetchType.LAZY)
    public List<Goods> getGoods(){
        return goods;
    }

    public void setGoods(List<Goods> goods) {
        this.goods = goods;
    }

    public Categories(){}

    public Categories(String nameOfCategory){
        this.nameOfCategory = nameOfCategory;
    }

    public Categories(String nameOfCategory, String descriptionOfCategorye){
        this.nameOfCategory = nameOfCategory;
        this.descriptionOfCategorye = descriptionOfCategorye;
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
    public String getDescriptionOfCategorye() {
        return descriptionOfCategorye;
    }

    public void setDescriptionOfCategorye(String descriptionOfCategorye) {
        this.descriptionOfCategorye = descriptionOfCategorye;
    }

    @Override
    public String toString() {
        return "Categories{" +
                "categoryId=" + categoryId +
                ", nameOfCategory='" + nameOfCategory + '\'' +
                ", descriptionOfCategorye='" + descriptionOfCategorye + '\'' +
                '}';
    }
}
