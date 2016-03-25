package ru.ncedu.ejb;

import ru.ncedu.bean.CategoriesB;
import ru.ncedu.bean.MarketManager;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Created by Павел on 23.03.2016.
 */
@Stateless
public class addCategories {
    @Inject
    MarketManager marketManager;

    public String addCategories(){
        marketManager.addCategory(new CategoriesB("Phones","description of Phones"));
        marketManager.addCategory(new CategoriesB("TV","description of TV"));
        marketManager.addCategory(new CategoriesB("Refrigerators","description of Refrigerators"));
        marketManager.addCategory(new CategoriesB("Computers","description of Computers"));
        marketManager.addCategory(new CategoriesB("Map-cases","description of Map-cases"));
        marketManager.addCategory(new CategoriesB("Players","description of Players"));
        marketManager.addCategory(new CategoriesB("Mouses","description of Mouses"));
        return null;
    }
}
