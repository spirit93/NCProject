package ru.ncedu.bean;

import ru.ncedu.ejb.regCheckEjb;
import ru.ncedu.entity.Products;
import ru.ncedu.entity.User;
import ru.ncedu.service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Павел
 */
@Stateless
public class UserManager implements Serializable {

    @Inject
    regCheckEjb reg;

    public UserManager() {
    }

    public ru.ncedu.bean.User getUserByUserName( String userName){
        User us = UserService.getUserByName(userName);
        if (us != null){
            return new ru.ncedu.bean.User(us);
        }
        return null;
    }

    public List<ru.ncedu.bean.User> getAllUsers(){
        List<ru.ncedu.entity.User> users = UserService.getAllUsers();

        List<ru.ncedu.bean.User> resultList= new ArrayList<ru.ncedu.bean.User>(users.size());
        for (ru.ncedu.entity.User user: users){
            resultList.add(new ru.ncedu.bean.User(user));
        }

        return resultList;
    }

    public List<ru.ncedu.bean.UserType> getAllUserTypes(){
        List<ru.ncedu.entity.UserType> userTypes = UserService.getAllTypes();

        List<ru.ncedu.bean.UserType> resultListUT= new ArrayList<UserType>(userTypes.size());
        for (ru.ncedu.entity.UserType userType: userTypes){
            resultListUT.add(new ru.ncedu.bean.UserType(userType));
        }

        return resultListUT;
    }

    public void addUser(ru.ncedu.bean.User user) {

        UserService.addUser(new User(user.getUserName(),user.getPassword(),user.getEmail()));
    }

    public void addTestUser() {
        UserService.addUser(new User("name","itIsPas","itIsMail"));
    }

    public List<ru.ncedu.bean.Products> getAllProducts(){
        List<ru.ncedu.entity.Products> products = UserService.getAllProducts();

        List<ru.ncedu.bean.Products> resultListProd = new ArrayList<ru.ncedu.bean.Products>(products.size());
        for (ru.ncedu.entity.Products product: products){
            resultListProd.add(new ru.ncedu.bean.Products(product));
        }
        return resultListProd;
    }

    public List<ru.ncedu.bean.Providers> getAllProviders(){

        List<ru.ncedu.entity.Providers> providers = UserService.getAllProviders();

        List<ru.ncedu.bean.Providers> resultListProviders = new ArrayList<ru.ncedu.bean.Providers>(providers.size());
        for (ru.ncedu.entity.Providers providers1: providers) {
            resultListProviders.add(new ru.ncedu.bean.Providers(providers1.getProviderId(), providers1.getCompanyName(), providers1.getPhoneNumber(), providers1.getEmailOfCompany(), providers1.getAdressOfCompany(), providers1.getWebsiteOfCompany()));
        }
        return resultListProviders;
    }

    public void addProviders(ru.ncedu.bean.Providers providers){
        if (providers == null){
            return;
        }
        UserService.addProviders(new ru.ncedu.entity.Providers(providers.getCompanyName(), providers.getPhoneNumber(), providers.getEmailOfCompany(), providers.getAdressOfCompany(), providers.getWebsiteOfCompany()));
    }

    public void addProducts(Products products){
        if (products == null){
            return;
        }
        ru.ncedu.entity.Providers providers = (products.getProviders() == null) ? null: UserService.getProvidersByCompanyName(products.getProviders().getCompanyName());
        UserService.addProducts(new ru.ncedu.entity.Products(products.getNameOfProduct(), products.getAmountOfProducts(), products.getPricePerOne(), products.getAmountOfOrders(), products.getImage(), products.getProviders(), products.getCategories()));
    }

    public ru.ncedu.bean.Categories getCategoryByNameOfCategory(String nameOfCategory){
        ru.ncedu.entity.Categories c = UserService.getCategoryByName(nameOfCategory);
        if (c != null){
            return new ru.ncedu.bean.Categories(c);
        }
        return null;
    }

    public ru.ncedu.bean.Providers getProvidersByName(String companyName){
        ru.ncedu.entity.Providers p = UserService.getProvidersByCompanyName(companyName);
        if (p != null){
            return new ru.ncedu.bean.Providers(p);
        }
        return null;
    }

    public List<ru.ncedu.bean.Categories> getAllCategories(){

        List<ru.ncedu.entity.Categories> categories = UserService.getAllCategories();

        List<ru.ncedu.bean.Categories> resultListCategories = new ArrayList<ru.ncedu.bean.Categories>(categories.size());
        for (ru.ncedu.entity.Categories categories1: categories) {
            resultListCategories.add(new ru.ncedu.bean.Categories(categories1.getCategoryId(), categories1.getNameOfCategory(), categories1.getDescriptionOfCategory()));
        }
        return resultListCategories;
    }

    public void addCategories(ru.ncedu.bean.Categories categories){
        if (categories == null){
            return;
        }
        UserService.addCategories(new ru.ncedu.entity.Categories(categories.getNameOfCategory(), categories.getDescriptionOfCategory()));
    }

    public List<ru.ncedu.bean.Orders> getAllOrders(){
        List<ru.ncedu.entity.Orders> orders = UserService.getAllOrders();

        List<ru.ncedu.bean.Orders> resultListOrders= new ArrayList<ru.ncedu.bean.Orders>(orders.size());
        for (ru.ncedu.entity.Orders order: orders){
            resultListOrders.add(new ru.ncedu.bean.Orders(order));
        }

        return resultListOrders;
    }

    public void addOrders(ru.ncedu.bean.Orders orders) {

        UserService.addOrders(new ru.ncedu.entity.Orders(orders.getNumberOfOrders(), orders.getDiscount(), orders.getDate()));
    }

}
