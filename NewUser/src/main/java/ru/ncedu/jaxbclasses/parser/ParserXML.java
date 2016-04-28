package ru.ncedu.jaxbclasses.parser;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import ru.ncedu.entity.*;
import ru.ncedu.jaxbclasses.*;
import ru.ncedu.service.MarketService;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;
import javax.xml.bind.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * Created by Dan Smirnov on 08.04.2016.
 */
@ManagedBean
@SessionScoped
public class ParserXML {
    private File imagesZip = new File("images.zip");
    private StreamedContent content;
    private Part file1;
    private File file = new File("databaseMarket.xml");

    {

        try {
            content = new DefaultStreamedContent(new FileInputStream(file),"application/xml","database.xml");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    public File getImagesZip(){ return imagesZip; }
    public void setImagesZip(File aImagesZip) { this.imagesZip = aImagesZip; }
    public StreamedContent getContent() { return content; }
    public void setContent(StreamedContent content) { this.content = content; }
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
        for (ProvidersJAXB providersEntity : marketLists.getProvidersList()) {
            MarketService.addProvider(new Providers(providersEntity));
        }
        for(int i=0; i<marketLists.getProductDetailsList().size(); i++){
            MarketService.addProduct(new Products(marketLists.getProductsList().get(i)), new ProductDetails(marketLists.getProductDetailsList().get(i)));
        }
    }

    /*public void uploadImages(){
        try {
            byte[] buffer = new byte[4096];
            int bytesRead = 0;
            InputStream inputStream = file1.getInputStream();
            FileOutputStream outputStream = new FileOutputStream(imagesZip.getName());
            while(true) {
                bytesRead = inputStream.read(buffer);
                if(bytesRead > 0) {
                    outputStream.write(buffer, 0, bytesRead);
                }else {
                    break;
                }
            }
            outputStream.close();
            inputStream.close();
            String pathToFolder = "images";
            File folder =new File(pathToFolder);
            if(folder.isDirectory()==false){
                File ff = new File(pathToFolder+"\\");
                ff.mkdirs();
            }
            ZipFile zipFile =new ZipFile(imagesZip.getName());
            Iterator<Object> iter = (Iterator<Object>) zipFile.entries();
            Object obj;
            while(iter.hasNext()) {
                obj = iter.next();
                FileOutputStream fout = new FileOutputStream(pathToFolder + obj.toString());
                ZipEntry zipEntry = new ZipEntry(obj.toString());
                InputStream is = zipFile.getInputStream(zipEntry);
                ZipInputStream zin = new ZipInputStream(zipFile.getInputStream(zipEntry));
                int j = 0;
                while (j != -1) {
                    j = is.read();
                    fout.write(j);
                }
                zin.close();
                fout.close();
            }
            zipFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    public void upload() {
        try {
            byte[] buffer = new byte[4096];
            int bytesRead = 0;
            InputStream inputStream = file1.getInputStream();
            FileOutputStream outputStream = new FileOutputStream(file.getName());
            while(true) {
                bytesRead = inputStream.read(buffer);
                if(bytesRead > 0) {
                    outputStream.write(buffer, 0, bytesRead);
                }else {
                    break;
                }
            }
            outputStream.close();
            inputStream.close();
            importDb();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

}
