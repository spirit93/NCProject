package ru.ncedu.ejb;

import ru.ncedu.bean.*;
import ru.ncedu.entity.Products;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by Павел on 30.03.2016.
 */
@Stateless
public class AddProduct {
    @Inject
    MarketManager marketManager;

    public String addProd(ProductsB product, ProdDetailsB detailsB){
        boolean isSameCat = false;
        boolean isSameBrand = false;

        List<CategoriesB> listCat =  marketManager.getAllCategories();
        List<ProvidersB> listBrend = marketManager.getAllProviders();

        for (CategoriesB cat:listCat){
            if (cat.getNameOfCategory().equals(product.getCategoryName())){
                isSameCat = true;
            }
        }

        for (ProvidersB prov : listBrend){
            if (prov.getCompanyName().equals(product.getProviderName())){
                isSameBrand = true;
            }
        }

        if (!isSameCat){
            FacesContext.getCurrentInstance().addMessage("product:categoryP", new FacesMessage(null, "Category no found"));
            return "Err";
        }
        if (!isSameBrand){
            FacesContext.getCurrentInstance().addMessage("product:brandP", new FacesMessage(null, "Brand not found"));
            return "Err";
        }

        marketManager.addProduct(product,detailsB);
        return "success";
    }
}
