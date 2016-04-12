package ru.ncedu.ejb;

import ru.ncedu.bean.CategoriesB;
import ru.ncedu.bean.MarketManager;
import ru.ncedu.bean.ProductsB;
import ru.ncedu.entity.Products;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by Павел on 23.03.2016.
 */
@Stateless
public class AddCategories {
    @Inject
    MarketManager marketManager;

    public String addCategories(){
        marketManager.addCategory(new CategoriesB("Phones","description of Phones"));
        marketManager.addCategory(new CategoriesB("TV","description of TV"));
        marketManager.addCategory(new CategoriesB("Refrigerators","description of Refrigerators"));
        marketManager.addCategory(new CategoriesB("Computers","description of Computers"));
        marketManager.addCategory(new CategoriesB("Laptops","description of Laptops"));
        marketManager.addCategory(new CategoriesB("Players","description of Players"));
        marketManager.addCategory(new CategoriesB("Mouses","description of Mouses"));
        return null;
    }

    public List<ProductsB> getCategoryProd(String nameOfCateg){
        return marketManager.getAllProductsBeansOfCategory(nameOfCateg);
    }

    public List<Products> getCategoryProdEntyties(String nameOfCateg){
        return marketManager.getAllProductsEntOfCategory(nameOfCateg);
    }
}
