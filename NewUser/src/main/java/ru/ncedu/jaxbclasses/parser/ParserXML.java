package ru.ncedu.jaxbclasses.parser;

import ru.ncedu.entity.Categories;
import ru.ncedu.entity.ProductDetails;
import ru.ncedu.entity.Products;
import ru.ncedu.entity.Providers;
import ru.ncedu.jaxbclasses.*;
import ru.ncedu.service.MarketService;

import javax.servlet.http.Part;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Dan Smirnov on 08.04.2016.
 */
public class ParserXML {
    private Part file1;
    private File file = new File("databaseMarket.xml");

    public Part getFile1() {
        return file1;
    }
    public void setFile1(Part file) {
        this.file1 = file;
    }

    private Object getObject(File file, Class c) throws JAXBException
    {
        JAXBContext context = JAXBContext.newInstance(c);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return unmarshaller.unmarshal(file);
    }

    private void saveObject(File file, Object o) throws JAXBException{
        JAXBContext context = JAXBContext.newInstance(o.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.marshal(o,file);
    }

    public void export() throws JAXBException {
        List<Categories> categoryEntityList = MarketService.getAllCategories();                 //pull out lists of entities
        List<ProductDetails> productDetailsEntityList = MarketService.getAllProductDetails();
        List<Products> productsEntityList = MarketService.getAllProducts();
        List<Providers> providersEntityList = MarketService.getAllProviders();

        List<CategoriesJAXB> jaxbFromEntitiesCategories = new ArrayList<>(categoryEntityList.size());// make an arrays of jaxb classes
        for (int i=0; i< categoryEntityList.size(); i++){
            jaxbFromEntitiesCategories.add(new CategoriesJAXB(categoryEntityList.get(i)));
        }
        List<ProductDetailsJAXB> jaxbFromEntitiesProductDetails = new ArrayList<>(productDetailsEntityList.size());
        for(int i=0; i<productDetailsEntityList.size(); i++){
            jaxbFromEntitiesProductDetails.add(new ProductDetailsJAXB(productDetailsEntityList.get(i)));
        }
        List<ProductsJAXB> jaxbFromEntitiesProducts = new ArrayList<>(productsEntityList.size());
        for(int i=0; i<productsEntityList.size(); i++){
            jaxbFromEntitiesProducts.add(new ProductsJAXB(productsEntityList.get(i)));
        }
        List<ProvidersJAXB> jaxbFromEntitiesProviders = new ArrayList<>(providersEntityList.size());
        for(int i=0; i<providersEntityList.size(); i++){
            jaxbFromEntitiesProviders.add(new ProvidersJAXB(providersEntityList.get(i)));
        }

        MarketJAXBLists marketLists = new MarketJAXBLists(jaxbFromEntitiesCategories, jaxbFromEntitiesProductDetails, jaxbFromEntitiesProducts, jaxbFromEntitiesProviders);
        saveObject(file, marketLists);
    }

    public void importDb() throws JAXBException {
        MarketJAXBLists marketLists = (MarketJAXBLists) getObject(file, MarketJAXBLists.class);

        for (CategoriesJAXB categoryEntity : marketLists.getCategoryList()) {
            MarketService.addCategory(new Categories(categoryEntity));
        }
        for(int i=0; i<marketLists.getProductDetailsList().size(); i++){
            MarketService.addProduct(new Products(marketLists.getProductsList().get(i)), new ProductDetails(marketLists.getProductDetailsList().get(i)));
        }
        for (ProvidersJAXB providersEntity : marketLists.getProvidersList()) {
            MarketService.addProvider(new Providers(providersEntity));
        }
    }

    public void upload() {
        try {
            String fileContent = new Scanner(this.file1.getInputStream())
                    .useDelimiter("\\A").next();


        } catch (IOException e) {
            // Error handling
        }
    }

}
