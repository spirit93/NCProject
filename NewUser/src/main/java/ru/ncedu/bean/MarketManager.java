package ru.ncedu.bean;

import ru.ncedu.entity.*;
import ru.ncedu.service.MarketService;
import ru.ncedu.service.UserService;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

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

    public List<ProductsB> getAllProductsBeansOfCategory(String nameOfCategory){
        List<Products> queryList = MarketService.getAllCategoryProducts(nameOfCategory);

        List<ProductsB> res = new ArrayList<>(queryList.size());
        for (Products p:queryList){
            res.add(new ProductsB(p));//!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        }

        return res;
    }

    public List<Products> getAllProductsEntOfCategory(String nameOfCategory){
        List<Products> queryList = MarketService.getAllCategoryProducts(nameOfCategory);
        return queryList;
    }

    public void addProduct(ProductsB product,ProdDetailsB detailsB){

        ru.ncedu.entity.Categories category = (product.getCategoryName() == null) ? null
                :MarketService.getCategoryByName(product.getCategoryName());

        ru.ncedu.entity.Providers provider = (product.getProviderName() == null) ? null
                :MarketService.getProviderByName(product.getProviderName());

        Products products = new Products(product.getNameOfProduct(),category,provider);
        ProductDetails details = new ProductDetails(detailsB);

        MarketService.addProduct(products,details);
    }
    public void addOrder(OrderBean orderBean, User userBean){
        ru.ncedu.entity.User user = UserService.getUserByEmail(userBean.getEmail());
        Order order = new Order(orderBean);
        order.setUser(user);
        MarketService.addOrder(order);
    }
}
