package ru.ncedu.bean;

import ru.ncedu.entity.Categories;
import ru.ncedu.entity.ProductDetails;
import ru.ncedu.entity.Products;
import ru.ncedu.entity.Providers;
import ru.ncedu.service.MarketService;

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

    public List<ProductsB> getAllProductsBOfCategory(String nameOfCategory){
        List<Products> queryList = MarketService.getAllCategoryProducts(nameOfCategory);

        List<ProductsB> res = new ArrayList<>(queryList.size());
        for (Products p:queryList){
            res.add(new ProductsB(p));
        }

        System.out.println(res);
        return res;
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
}
