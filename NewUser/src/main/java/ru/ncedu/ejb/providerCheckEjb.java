package ru.ncedu.ejb;

import ru.ncedu.bean.MarketManager;
import ru.ncedu.bean.ProvidersB;
import ru.ncedu.entity.Products;
import ru.ncedu.service.MarketService;
import ru.ncedu.service.Validator;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Created by Павел on 19.03.2016.
 */
@Stateless
public class providerCheckEjb {
    @Inject
    MarketManager marketManager;
    @Inject
    Validator validator;

    public Products getProductByName(String prdName){
        Products product  = MarketService.getProductByName(prdName);
        if (product == null){
            return null;
        }
        return product;
    }

    public String addProvider(String name,String phone,String mail,String country,String web){
        if (!validator.isEmailValid(mail)){
            return "wrongEmail";
        }else if(!validator.isPhoneValid(phone)){
            return "wrongPhone";
        }

        ProvidersB provider = new ProvidersB(name,phone,mail,country,web);
        marketManager.addProvider(provider);
        return "ProviderAdded";
    }
}
