package ru.ncedu.service;

import ru.ncedu.bean.ProdDetailsB;
import ru.ncedu.entity.*;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.io.IOError;
import java.io.IOException;
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

    public static Providers getProviderByName(String name) {
        TypedQuery<Providers> query = em.createNamedQuery("Providers.getProviderByName", Providers.class);
        query.setParameter("companyName", name);

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
    public static List<Products> getAllProducts() {
        return em.createNamedQuery("Products.getAllProducts", Products.class).getResultList();
    }

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
        Categories categories = products.getCategories();
        List<Products> prList = categories.getProducts();

        ProductDetails details1 = em.merge(details);
        products.setProductDetails(details1);

        Products result = em.merge(products);

        prList.add(result);
        categories.setProducts(prList);
        em.merge(categories);

        em.getTransaction().commit();
        return result;
    }

    //-------------productDetails---------------------
    public static List<ProductDetails> getAllProductDetails(){
        return em.createNamedQuery("ProductDetails.getAllProductDetails", ProductDetails.class).getResultList();
    }

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

    public static Categories getCategoryByName(String name) {
        TypedQuery<Categories> query = em.createNamedQuery("Categories.getCategoryByName", Categories.class);
        query.setParameter("nameOfCategory", name);

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

    public static List<Products> getAllCategoryProducts(String nameOfCategory){
        TypedQuery<Categories> query = em.createNamedQuery("Categories.getCategoryByName", Categories.class);
        query.setParameter("nameOfCategory", nameOfCategory);

        Categories category = null;
        try{
            category = query.getSingleResult();
        }catch (NoResultException ignore){
        }

        if (category == null){
            return null;
        }

        return category.getProducts();
    }

    //---------------orders
    public static Order addOrder(Order order){
        em.getTransaction().begin();
//        user = em.merge(user);
//        order.setUser(user);

        Order result = em.merge(order);
        em.getTransaction().commit();
        return result;
    }
    public static List<Order> getAllOrders(){
        return em.createNamedQuery("Order.getAllOrders",Order.class).getResultList();
    }
    public static List<Products> getAllOrderedProducts(){
        return em.createNamedQuery("Order.getAllOrderedProducts", Products.class).getResultList();
    }
}
