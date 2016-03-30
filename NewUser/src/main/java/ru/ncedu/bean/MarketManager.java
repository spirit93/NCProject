package ru.ncedu.bean;

import ru.ncedu.ejb.addProduct;
import ru.ncedu.entity.Categories;
import ru.ncedu.entity.Providers;
import ru.ncedu.service.MarketService;
import ru.ncedu.service.UserService;

import javax.ejb.Stateless;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Павел on 19.03.2016.
 */
@Stateless
public class MarketManager {
//    @Inject
//    addProduct addProductE;

    public MarketManager() {
    }

    public void addProvider(ProvidersB provider){
        Providers providers = new Providers(provider);
        MarketService.addProvider(providers);
    }

    public List<ProvidersB> getAllProviders(){
        List<ru.ncedu.entity.Providers> providers = MarketService.getAllProviders();

        List<ProvidersB> resultList= new ArrayList<ProvidersB>(providers.size());
        for (Providers provider: providers){
            resultList.add(new ProvidersB(provider));
        }

        return resultList;
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

    public List<CategoriesB> getAllCategories(){
        List<ru.ncedu.entity.Categories> categories = MarketService.getAllCategories();

        List<CategoriesB> resultList= new ArrayList<CategoriesB>(categories.size());
        for (Categories category: categories){
            resultList.add(new CategoriesB(category));
        }

        return resultList;
    }

}
