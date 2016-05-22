package ru.ncedu.bean;

import ru.ncedu.entity.Products;
import ru.ncedu.entity.Providers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Павел on 19.03.2016.
 */
@ManagedBean
@RequestScoped
public class ProvidersB {

    private List<ProductsB> products;
    private long providerId;
    private String companyName;
    private String phoneNumber;
    private String emailOfCompany;
    private String adressOfCompany;
    private String websiteOfCompany;

    public ProvidersB() {
    }

    public ProvidersB(Providers provider) {
        this.companyName = provider.getCompanyName();
        this.phoneNumber = provider.getPhoneNumber();
        this.emailOfCompany = provider.getEmailOfCompany();
        this.adressOfCompany = provider.getAdressOfCompany();
        this.websiteOfCompany = provider.getWebsiteOfCompany();
    }


    public ProvidersB(String companyName, String phoneNumber, String emailOfCompany,
                      String adressOfCompany, String websiteOfCompany) {
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.emailOfCompany = emailOfCompany;
        this.adressOfCompany = adressOfCompany;
        this.websiteOfCompany = websiteOfCompany;
    }

    public ProvidersB(String companyName, String phoneNumber, String emailOfCompany,
                      String adressOfCompany, String websiteOfCompany, List<ProductsB> products) {
        this.products = products;
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.emailOfCompany = emailOfCompany;
        this.adressOfCompany = adressOfCompany;
        this.websiteOfCompany = websiteOfCompany;
    }
    
    //------------------------------
    public List<ProductsB> getProducts() {
        return products;
    }

    public void setProducts(List<ProductsB> products) {
        this.products = products;
    }

    public long getProviderId() {
        return providerId;
    }

    public void setProviderId(long providerId) {
        this.providerId = providerId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailOfCompany() {
        return emailOfCompany;
    }

    public void setEmailOfCompany(String emailOfCompany) {
        this.emailOfCompany = emailOfCompany;
    }

    public String getAdressOfCompany() {
        return adressOfCompany;
    }

    public void setAdressOfCompany(String adressOfCompany) {
        this.adressOfCompany = adressOfCompany;
    }

    public String getWebsiteOfCompany() {
        return websiteOfCompany;
    }

    public void setWebsiteOfCompany(String websiteOfCompany) {
        this.websiteOfCompany = websiteOfCompany;
    }

}
