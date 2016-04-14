package ru.ncedu.ejb;

import ru.ncedu.bean.MarketManager;
import ru.ncedu.bean.ProvidersB;
import ru.ncedu.entity.Products;
import ru.ncedu.service.MarketService;
import ru.ncedu.service.Validator;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 * Created by Павел on 19.03.2016.
 */
@Stateless
public class ProviderCheckEjb {
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
//        if (!validator.isEmailValid(mail)){
//            return "wrongEmail";
//        }else if(!validator.isPhoneValid(phone)){
//            return "wrongPhone";
//        }

        ProvidersB provider = new ProvidersB(name,phone,mail,country,web);
        marketManager.addProvider(provider);
        return "ProviderAdded";
    }

    public void initProviders(){
        ProvidersB provider1 = new ProvidersB("samsung","8-800","123@mail.ru","Koreya","www.leningrad");
        ProvidersB provider2 = new ProvidersB("iphone","8-921","456@mail.ru","USA","www.ggg.ru");
        ProvidersB provider3 = new ProvidersB("Nokia","8-925","789@mail.ru","Alaska","www.connectedPeople.com");
        marketManager.addProvider(provider1);
        marketManager.addProvider(provider2);
        marketManager.addProvider(provider3);
    }

    public String addProvider(ProvidersB provider){
        if (!validator.isEmailValid(provider.getEmailOfCompany())){
            FacesContext.getCurrentInstance().addMessage("brand:email", new FacesMessage(null, "Email is wrong"));
            return "Err";
        }else if(!validator.isPhoneValid(provider.getPhoneNumber())){
            FacesContext.getCurrentInstance().addMessage("brand:phone", new FacesMessage(null, "Phone is wrong"));
            return "Err";
        }

        marketManager.addProvider(provider);
        return "ProviderAdded";
    }
}
