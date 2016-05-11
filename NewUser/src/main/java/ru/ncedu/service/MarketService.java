package ru.ncedu.service;

import org.primefaces.model.UploadedFile;
import ru.ncedu.bean.ProdDetailsB;
import ru.ncedu.entity.*;
import ru.ncedu.utils.archiver.ArchiverImpl;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Павел on 19.03.2016.
 */
@Stateless
public class MarketService extends  Service{
    //-------------provider
    public static Providers addProvider(Providers provider){
        em.getTransaction().begin();
        Providers result = em.merge(provider);
        em.getTransaction().commit();
        return result;
    }
    public static Providers getProviderById(int id) {
        TypedQuery<Providers> query = em.createNamedQuery("Providers.getProviderById", Providers.class);
        query.setParameter("providerId", id);

        Providers provider = null;
        try{
            provider = query.getSingleResult();
        }catch (NoResultException ignore){
        }

        if (provider == null){
            return null;
        }
        return provider;
    }

    public static Providers getProviderByName(String name) {
        TypedQuery<Providers> query = em.createNamedQuery("Providers.getProviderByName", Providers.class);
        query.setParameter("companyName", name);

        Providers provider = null;
        try{
            provider = query.getSingleResult();
        }catch (NoResultException ignore){
        }

        if (provider == null){
            return null;
        }
        return provider;
    }

    public static List<Providers> getAllProviders() {
        return em.createNamedQuery("Providers.getAllProviders",Providers.class).getResultList();
    }

    //-------------products
    public static List<Products> getAllProducts() {
        return em.createNamedQuery("Products.getAllProducts", Products.class).getResultList();
    }

    public static Products getProductByName(String prdName){
        TypedQuery<Products> query = em.createNamedQuery("Product.getProductByName",Products.class);
        query.setParameter("nameOfProduct",prdName);

        Products result= null;
        try{
            result = query.getSingleResult();
        }catch (NoResultException ignore){
        }

        if(result == null){return null;}

        return result;
    }

    public static Products addProduct(Products products,ProductDetails details){
        em.getTransaction().begin();
        Categories categories = products.getCategories();
        List<Products> prList = categories.getProducts();

        ProductDetails details1 = em.merge(details);
        products.setProductDetails(details1);

        Products result = em.merge(products);

        prList.add(result);
        categories.setProducts(prList);
        em.merge(categories);

        em.getTransaction().commit();
        return result;
    }

    public static Products getProdDetailsByProdId(int idOfProd){
        TypedQuery<Products> query =  em.createNamedQuery("Products.getProdById", Products.class);
        query.setParameter("productsId", (long)idOfProd);

        Products products = null;
        try{
            products = query.getSingleResult();
        }catch (NoResultException ignore){
        }

        if (products == null){
            return null;
        }
        return products;
    }

    //-------------productDetails---------------------
    public static List<ProductDetails> getAllProductDetails(){
        return em.createNamedQuery("ProductDetails.getAllProductDetails", ProductDetails.class).getResultList();
    }

    public static void incrementAmountOfProducts(long prodId){
        em.getTransaction().begin();
        TypedQuery<Products> query = em.createNamedQuery("Products.getProductById", Products.class);
        query.setParameter("productsId", prodId);
        Products prod = query.getSingleResult();
        ProductDetails prodDetails = prod.getProductDetails();
        prodDetails.setAmountOfProducts(prodDetails.getAmountOfProducts()+1);
        em.merge(prodDetails);
        em.getTransaction().commit();
    }

    public static void decrementAmountOfProducts(long prodId) throws IllegalArgumentException{
        em.getTransaction().begin();
        TypedQuery<Products> query = em.createNamedQuery("Products.getProductById", Products.class);
        query.setParameter("productsId", prodId);
        Products prod = query.getSingleResult();
        ProductDetails prodDetails = prod.getProductDetails();
        if(prodDetails.getAmountOfProducts()<=0){ //it shouldn't happen, because we don't show products with empty amount on the site
            throw new IllegalArgumentException();
        }
        prodDetails.setAmountOfProducts(prodDetails.getAmountOfProducts()-1);
        em.merge(prodDetails);
        em.getTransaction().commit();
    }

    //--------------- categories -------------------
    public static Categories addCategory(Categories cat) {
        em.getTransaction().begin();
        Categories result = em.merge(cat);
        em.getTransaction().commit();
        return result;
    }

    public static List<Categories> getAllCategories() {
        return em.createNamedQuery("Categories.getAllCategories",Categories.class).getResultList();
    }

    public static Categories getCategoryById(int id) {
        TypedQuery<Categories> query = em.createNamedQuery("Categories.getCategoryById", Categories.class);
        query.setParameter("categoryId", (long)id);

        Categories category = null;
        try{
            category = query.getSingleResult();
        }catch (NoResultException ignore){
        }

        if (category == null){
            return null;
        }
        return category;
    }

    public static Categories getCategoryByName(String name) {
        TypedQuery<Categories> query = em.createNamedQuery("Categories.getCategoryByName", Categories.class);
        query.setParameter("nameOfCategory", name);

        Categories category = null;
        try{
            category = query.getSingleResult();
        }catch (NoResultException ignore){
        }

        if (category == null){
            return null;
        }
        return category;
    }

    public static List<Products> getAllCategoryProducts(String nameOfCategory){
        TypedQuery<Categories> query = em.createNamedQuery("Categories.getCategoryByName", Categories.class);
        query.setParameter("nameOfCategory", nameOfCategory);

        Categories category = null;
        try{
            category = query.getSingleResult();
        }catch (NoResultException ignore){
        }

        if (category == null){
            return null;
        }
        List<Products> notEmptyProducts = new ArrayList<>();
        for(Products product: category.getProducts()){
            if(product.getProductDetails().getAmountOfProducts()>0){
                notEmptyProducts.add(product);
            }
        }
        return notEmptyProducts;
    }

    //---------------orders

    public static List<Order> getAllOrders(){
        return em.createNamedQuery("Order.getAllOrders",Order.class).getResultList();
    }
    public static List<Products> getAllOrderedProducts(){
        return em.createNamedQuery("Order.getAllOrderedProducts", Products.class).getResultList();
    }
    public static Order addOrder(Order order){
        em.getTransaction().begin();
//        user = em.merge(user);
//        order.setUser(user);

        Order result = em.merge(order);
        em.getTransaction().commit();
        return result;
    }

    public static void changeStatusOfOrder(int orderId,int i){
        em.getTransaction().begin();
        TypedQuery <Order> query =  em.createNamedQuery("Order.getOrderById", Order.class);
        query.setParameter("orderId",orderId);
        Order order = query.getSingleResult();
        order.setStatus(Order.StatusOrd.values()[i]);
        em.merge(order);
        em.getTransaction().commit();
        if(i==2){
            incrementAmountOfProducts(order.getIdOfProd());
        }
    }

    public static ProductDetails changeAmountInSaplay(ru.ncedu.bean.User user, int changeTo){
        em.getTransaction().begin();
        ProductDetails result = getProdDetailsByProdId(user.getId()).getProductDetails();
        result.setAmountOfProducts(changeTo);
        em.merge(result);
        em.getTransaction().commit();
        return result;
    }

    //-----------------Archiver

    private File imagesZip = new File("images.zip");

    public File getImagesZip() {
        return imagesZip;
    }

    public void setImagesZip(File imagesZip) {
        this.imagesZip = imagesZip;
    }

    public  static void zipFolderOfImg(File imagesZip){
        ArchiverImpl archiver = new ArchiverImpl();
        List<String> files = new ArrayList<>();
        files.add(PropertiesClass.getProperties("pathToProject")+PropertiesClass.getProperties("pathToImgDir"));
        try {
            archiver.writeToZip(imagesZip.getPath(),files);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void unzipImgToImgFold(File zipF){
//        File zipF = new File(PropertiesClass.getProperties("pathToDownloads")+zipFile.getSubmittedFileName());
//        File zipF = new File(PropertiesClass.getProperties("pathToDownloads")+zipFile.getSubmittedFileName());
        ArchiverImpl archiver = new ArchiverImpl();
        try {
            archiver.unpackZipArchiv(zipF.getPath(),
                    PropertiesClass.getProperties("pathToProject")+PropertiesClass.getProperties("pathToImgDir"));
            archiver.unpackZipArchiv(zipF.getPath(),
                    PropertiesClass.getProperties("pathToGFImgFold"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
