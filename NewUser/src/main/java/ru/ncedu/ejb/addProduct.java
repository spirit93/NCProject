package ru.ncedu.ejb;

import ru.ncedu.bean.MarketManager;
import ru.ncedu.bean.ProdDetailsB;
import ru.ncedu.bean.ProductsB;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Created by Павел on 30.03.2016.
 */
@Stateless
public class addProduct {
    @Inject
    MarketManager marketManager;

    public void addProd(ProductsB product, ProdDetailsB detailsB){
        marketManager.addProduct(product,detailsB);
    }
}
