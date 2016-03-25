package ru.ncedu.bean;

import ru.ncedu.entity.Categories;
import ru.ncedu.entity.Providers;
import ru.ncedu.service.MarketService;
import ru.ncedu.service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Created by Павел on 19.03.2016.
 */
@Stateless
public class MarketManager {
    public MarketManager() {
    }

    public void addProvider(ProvidersB provider){
        Providers providers = new Providers(provider);
        MarketService.addProvider(providers);
    }


    public void addProvider(String name,String phone,String mail,String country,String web){
        ProvidersB provider = new ProvidersB(name,phone,mail,country,web);

        Providers providers = new Providers(provider);
        MarketService.addProvider(providers);
    }


    public void addCategory(CategoriesB category){
        Categories cat = new Categories(category.getNameOfCategory(),category.getDescriptionOfCategory());
        MarketService.addCategory(cat);
    }

}
