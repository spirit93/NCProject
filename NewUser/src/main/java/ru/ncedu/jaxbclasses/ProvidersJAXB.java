package ru.ncedu.jaxbclasses;

import ru.ncedu.entity.Providers;

import javax.persistence.Column;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by D.Smirnov on 08.04.2016.
 */
@XmlType(propOrder = {"providerId", "companyName", "phoneNumber", "emailOfCompany", "adressOfCompany", "websiteOfCompany"})
public class ProvidersJAXB {
    private long providerId;
    private String companyName;
    private String phoneNumber;
    private String emailOfCompany;
    private String adressOfCompany;
    private String websiteOfCompany;

    public ProvidersJAXB() {}

    public ProvidersJAXB(Providers providers){
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

    @XmlElement
    public void setProviderId(long providerId) {
        this.providerId = providerId;
    }

    public String getCompanyName() {
        return companyName;
    }

    @XmlElement
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @XmlElement
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailOfCompany() {
        return emailOfCompany;
    }

    @XmlElement
    public void setEmailOfCompany(String emailOfCompany) {
        this.emailOfCompany = emailOfCompany;
    }

    public String getAdressOfCompany() {
        return adressOfCompany;
    }

    @XmlElement
    public void setAdressOfCompany(String adressOfCompany) {
        this.adressOfCompany = adressOfCompany;
    }

    public String getWebsiteOfCompany() {
        return websiteOfCompany;
    }

    @XmlElement
    public void setWebsiteOfCompany(String websiteOfCompany) {
        this.websiteOfCompany = websiteOfCompany;
    }
}
