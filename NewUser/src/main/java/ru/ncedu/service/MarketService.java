package ru.ncedu.service;

import ru.ncedu.entity.Categories;
import ru.ncedu.entity.Products;
import ru.ncedu.entity.Providers;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.*;

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

    public static Categories addCategory(Categories cat) {
        em.getTransaction().begin();
        Categories result = em.merge(cat);
        em.getTransaction().commit();
        return result;
    }
}
