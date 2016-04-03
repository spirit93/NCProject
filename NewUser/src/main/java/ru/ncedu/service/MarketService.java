package ru.ncedu.service;

import ru.ncedu.bean.ProdDetailsB;
import ru.ncedu.entity.Categories;
import ru.ncedu.entity.ProductDetails;
import ru.ncedu.entity.Products;
import ru.ncedu.entity.Providers;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Павел on 19.03.2016.
 */
@Stateless
public class MarketService extends  Service{
    //-------------provider
    public static Providers addProvider(Providers provider){
        em.getTransaction().begin();
        Providers result = em.merge(provider);
        em.getTransaction().commit();
        return result;
    }
    public static Providers getProviderById(int id) {
        TypedQuery<Providers> query = em.createNamedQuery("Providers.getProviderById", Providers.class);
        query.setParameter("providerId", id);

        Providers provider = null;
        try{
            provider = query.getSingleResult();
        }catch (NoResultException ignore){
        }

        if (provider == null){
            return null;
        }
        return provider;
    }

    public static List<Providers> getAllProviders() {
        return em.createNamedQuery("Providers.getAllProviders",Providers.class).getResultList();
    }

    //-------------products
    public static Products getProductByName(String prdName){
        TypedQuery<Products> query = em.createNamedQuery("Product.getProductByName",Products.class);
        query.setParameter("nameOfProduct",prdName);

        Products result= null;
        try{
            result = query.getSingleResult();
        }catch (NoResultException ignore){
        }

        if(result == null){return null;}

        return result;
    }

    public static Products addProduct(Products products,ProductDetails details){
        em.getTransaction().begin();
        ProductDetails details1 = em.merge(details);
        products.setProductDetails(details1);

        Products result = em.merge(products);
        em.getTransaction().commit();
        return result;
    }

//
//    public static ProductDetails addProductDetails(ProductDetails detailsB) {
//        em.getTransaction().begin();
//        ProductDetails result = em.merge(detailsB);
//        em.getTransaction().commit();
//        return result;
//    }
    //--------------- categories -------------------
    public static Categories addCategory(Categories cat) {
        em.getTransaction().begin();
        Categories result = em.merge(cat);
        em.getTransaction().commit();
        return result;
    }

    public static List<Categories> getAllCategories() {
        return em.createNamedQuery("Categories.getAllCategories",Categories.class).getResultList();
    }

    public static Categories getCategoryById(int id) {
        TypedQuery<Categories> query = em.createNamedQuery("Categories.getCategoryById", Categories.class);
        query.setParameter("categoryId", (long)id);

        Categories category = null;
        try{
            category = query.getSingleResult();
        }catch (NoResultException ignore){
        }

        if (category == null){
            return null;
        }
        return category;
    }

}
