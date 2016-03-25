package ru.ncedu.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Алёна
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "Providers.getAllProviders", query = "SELECT p from providerstable p"),
        @NamedQuery(name = "Providers.getProvidersByCompanyName", query = "SELECT p from providerstable p WHERE p.companyName = :companyName")
}
)
@Table(name = "ProvidersTable")
public class Providers implements Serializable {
    private List<Products> products;
    private long providerId;
    private String companyName;
    private String phoneNumber;
    private String emailOfCompany;
    private String adressOfCompany;
    private String websiteOfCompany;

    @OneToMany(mappedBy = "providers", targetEntity = Products.class)
    public List<Products> getProducts(){
        return products;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
    }

    public Providers(){}

    public Providers(String companyName, String phoneNumber, String emailOfCompany, String adressOfCompany, String websiteOfCompany){
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.emailOfCompany = emailOfCompany;
        this.adressOfCompany = adressOfCompany;
        this.websiteOfCompany = websiteOfCompany;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    public long getProviderId() {
        return providerId;
    }

    public void setProviderId(long providerId) {
        this.providerId = providerId;
    }

    @Column
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Column
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Column
    public String getEmailOfCompany() {
        return emailOfCompany;
    }

    public void setEmailOfCompany(String emailOfCompany) {
        this.emailOfCompany = emailOfCompany;
    }

    @Column
    public String getAdressOfCompany() {
        return adressOfCompany;
    }

    public void setAdressOfCompany(String adressOfCompany) {
        this.adressOfCompany = adressOfCompany;
    }

    @Column
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

}