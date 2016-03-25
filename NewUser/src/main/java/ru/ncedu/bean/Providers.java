package ru.ncedu.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.Objects;

/**
 * Created by Алёна
 */
@ManagedBean
@RequestScoped
public class Providers {

    private long providerId;
    private String companyName;
    private String phoneNumber;
    private String emailOfCompany;
    private String adressOfCompany;
    private String websiteOfCompany;

    public Providers(){
    }

    public Providers(String companyName, String phoneNumber, String emailOfCompany, String adressOfCompany, String websiteOfCompany ){
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.emailOfCompany = emailOfCompany;
        this.adressOfCompany = adressOfCompany;
        this.websiteOfCompany = websiteOfCompany;
    }

    public Providers(long providerId, String companyName, String phoneNumber, String emailOfCompany, String adressOfCompany, String websiteOfCompany ){
        this.providerId = providerId;
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.emailOfCompany = emailOfCompany;
        this.adressOfCompany = adressOfCompany;
        this.websiteOfCompany = websiteOfCompany;
    }

    public Providers(ru.ncedu.entity.Providers providers){
        if(providers == null){
            return;
        }
        this.providerId = providers.getProviderId();
        this.companyName = providers.getCompanyName();
        this.phoneNumber = providers.getPhoneNumber();
        this.emailOfCompany = providers.getEmailOfCompany();
        this.adressOfCompany = providers.getAdressOfCompany();
        this.websiteOfCompany = providers.getWebsiteOfCompany();
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

    @Override
    public String toString() {
        return "Providers{" +
                "providerId=" + providerId +
                ", companyName='" + companyName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", emailOfCompany='" + emailOfCompany + '\'' +
                ", adressOfCompany='" + adressOfCompany + '\'' +
                ", websiteOfCompany='" + websiteOfCompany + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof Providers)) return false;
        Providers providers = (Providers) o;
        return  Objects.equals(providerId, providers.providerId) &&
                Objects.equals(companyName, providers.companyName) &&
                Objects.equals(phoneNumber, providers.phoneNumber) &&
                Objects.equals(emailOfCompany, providers.emailOfCompany) &&
                Objects.equals(adressOfCompany, providers.adressOfCompany) &&
                Objects.equals(websiteOfCompany, providers.websiteOfCompany);
    }
    @Override
    public int hashCode() {
        return Objects.hash(providerId, companyName, phoneNumber, emailOfCompany, adressOfCompany, websiteOfCompany);
    }
}
