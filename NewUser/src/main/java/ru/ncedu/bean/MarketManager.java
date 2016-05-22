package ru.ncedu.bean;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import ru.ncedu.entity.*;
import ru.ncedu.service.MarketService;
import ru.ncedu.service.PropertiesClass;
import ru.ncedu.service.UserService;

import javax.ejb.Stateless;
import javax.servlet.http.Part;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Павел on 19.03.2016.
 */
@Stateless
public class MarketManager {

    public MarketManager() {
    }

    public void addProvider(ProvidersB provider){
        Providers providers = new Providers(provider);
        MarketService.addProvider(providers);
    }

    public List<ProvidersB> getAllProviders(){
        List<ru.ncedu.entity.Providers> providers = MarketService.getAllProviders();

        List<ProvidersB> resultList= new ArrayList<ProvidersB>(providers.size());
        for (Providers provider: providers){
            resultList.add(new ProvidersB(provider));
        }

        return resultList;
    }


    public void addProvider(String name,String phone,String mail,String country,String web){
        ProvidersB provider = new ProvidersB(name,phone,mail,country,web);

        Providers providers = new Providers(provider);
        MarketService.addProvider(providers);
    }


    public void addCategory(CategoriesB category){
        Categories cat = new Categories(category.getNameOfCategory(),category.getDescriptionOfCategory());
        MarketService.addCategory(cat);
    }

    public List<CategoriesB> getAllCategories(){
        List<ru.ncedu.entity.Categories> categories = MarketService.getAllCategories();

        List<CategoriesB> resultList= new ArrayList<CategoriesB>(categories.size());
        for (Categories category: categories){
            resultList.add(new CategoriesB(category));
        }

        return resultList;
    }

    public List<ProductsB> getAllProductsBeansOfCategory(String nameOfCategory){
        List<Products> queryList = MarketService.getAllCategoryProducts(nameOfCategory);

        List<ProductsB> res = new ArrayList<>(queryList.size());
        for (Products p:queryList){
            res.add(new ProductsB(p));//!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        }

        return res;
    }

    public List<Products> getAllProductsEntOfCategory(String nameOfCategory){
        List<Products> queryList = MarketService.getAllCategoryProducts(nameOfCategory);
        return queryList;
    }

    public void addProduct(ProductsB product,ProdDetailsB detailsB){

        ru.ncedu.entity.Categories category = (product.getCategoryName() == null) ? null
                :MarketService.getCategoryByName(product.getCategoryName());

        ru.ncedu.entity.Providers provider = (product.getProviderName() == null) ? null
                :MarketService.getProviderByName(product.getProviderName());

        Products products = new Products(product.getNameOfProduct(),category,provider);
        ProductDetails details = new ProductDetails(detailsB);

        MarketService.addProduct(products,details);
    }
    public String addOrder(OrderBean orderBean, User userBean){
        ru.ncedu.entity.User user = UserService.getUserByEmail(userBean.getEmail());
//        ru.ncedu.entity.User user = UserService.getUserByName(UserStatus.getUser().getUserName());
        if (user == null){
            return "userNotLoggin";
        }else{
        Order order = new Order(orderBean);
        order.setUser(user);
        try {
            MarketService.decrementAmountOfProducts(order.getIdOfProd());
        }catch (IllegalArgumentException e){
            e.getMessage();
        }
        MarketService.addOrder(order);
        }
        return "well";
    }

    //-----------------Archiver

    private File imagesZip ;
    private StreamedContent content;
    private Part partToZip;

    {
        imagesZip = new File("images.zip");
        try {
            content = new DefaultStreamedContent(new FileInputStream(imagesZip),"application/zip","ImagesZip.zip");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public File getImagesZip() {
        return imagesZip;
    }

    public Part getPartToZip() {
        return partToZip;
    }

    public void setPartToZip(Part partToZip) {
        this.partToZip = partToZip;
    }

    public void setImagesZip(File imagesZip) {
        this.imagesZip = imagesZip;
    }

    public StreamedContent getContent() {
        return content;
    }

    public void setContent(StreamedContent content) {
        this.content = content;
    }

    public void zipImgs(){
        MarketService.zipFolderOfImg(getImagesZip());
    }

    public void unzipImgs(){
        String stringToZip = PropertiesClass.getProperties("pathToProject") + PropertiesClass.getProperties("pathToImgZip");
        File zipArchFold = new File(stringToZip);
            if (!zipArchFold.exists()){
                zipArchFold.mkdirs();
            }
        File zipArch = new File(stringToZip +"zipArch.zip");

        try (InputStream is = getPartToZip().getInputStream();FileOutputStream fos = new FileOutputStream(zipArch)){
            byte[] buf = new byte[1024];

            while (is.available() > 0) {
                int length = is.read(buf);
                fos.write(buf,0,length);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        MarketService.unzipImgToImgFold(zipArch);
        zipArch.delete();
        zipArchFold.delete();
    }

//    public void zipImgs(){
//        MarketService.zipFolderOfImg();
//    }
//
//    public void unzipImgs(){
//        MarketService.unzipImgToImgFold();
//    }
}
