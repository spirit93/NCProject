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
        marketManager.addCategory(new CategoriesB("Phones","description"));
        marketManager.addCategory(new CategoriesB("TV","description"));
        marketManager.addCategory(new CategoriesB("Phones","description"));
        marketManager.addCategory(new CategoriesB("Phones","description"));
        marketManager.addCategory(new CategoriesB("Phones","description"));
        marketManager.addCategory(new CategoriesB("Phones","description"));
        return null;
    }
}
