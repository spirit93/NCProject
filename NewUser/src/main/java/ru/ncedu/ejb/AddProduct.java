package ru.ncedu.ejb;

import ru.ncedu.bean.*;
import ru.ncedu.entity.Products;
import ru.ncedu.service.PropertiesClass;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.Part;
import java.io.*;
import java.util.List;

/**
 * Created by Павел on 30.03.2016.
 */
@Stateless
public class AddProduct {
    @Inject
    MarketManager marketManager;

    public String addProd(ProductsB product, ProdDetailsB detailsB){
        boolean isSameCat = false;
        boolean isSameBrand = false;

        List<CategoriesB> listCat =  marketManager.getAllCategories();
        List<ProvidersB> listBrend = marketManager.getAllProviders();

            for (CategoriesB cat:listCat){
                if (cat.getNameOfCategory().equals(product.getCategoryName())){
                    isSameCat = true;
                }
            }
            for (ProvidersB prov : listBrend){
                if (prov.getCompanyName().equals(product.getProviderName())){
                    isSameBrand = true;
                }
            }
            if (!isSameCat){
                FacesContext.getCurrentInstance().addMessage("product:categoryP", new FacesMessage(null, "Category no found"));
                return "Err";
            }
            if (!isSameBrand){
                FacesContext.getCurrentInstance().addMessage("product:brandP", new FacesMessage(null, "Brand not found"));
                return "Err";
            }

        String pathToProjImg = PropertiesClass.getProperties("pathToProject")+
                PropertiesClass.getProperties("pathToImgDir")+product.getCategoryName()+"\\";

            File folder = new File(pathToProjImg);{
                if (!folder.exists()){
                    folder.mkdirs();
                }
            }

        File img = new File(pathToProjImg+"\\" + detailsB.getFile().getSubmittedFileName());
            if (detailsB.getFile() != null){
                uploadImage(product,detailsB,pathToProjImg);

                    if (img.exists()) {
                        detailsB.setPathToImg("resources/img/categories/" + product.getCategoryName() + "/" + detailsB.getFile().getSubmittedFileName());
                    }else {
                        detailsB.setPathToImg("resources/img/defCategories/" + detailsB.getFile().getSubmittedFileName());
                    }
                }
        marketManager.addProduct(product,detailsB);
        return "success";
    }

    public void uploadImage(ProductsB product, ProdDetailsB prodDet,String pathToProjImg){
        String pathToGFimgs = PropertiesClass.getProperties("pathToGFImgFold")+product.getCategoryName()+"\\";
        File dirExists = new File(pathToGFimgs);

        if (!dirExists.exists()){
            dirExists.mkdirs();
        }
        try(FileOutputStream fos1 = new FileOutputStream(pathToProjImg + prodDet.getFile().getSubmittedFileName());
            FileOutputStream fos2 = new FileOutputStream(pathToGFimgs + prodDet.getFile().getSubmittedFileName());
            InputStream is = prodDet.getFile().getInputStream()){
            byte[] buf = new byte[1024];
                while(is.available()>0){
                    int length = is.read(buf);
                    fos1.write(buf,0,length);
                    fos2.write(buf,0,length);
                }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
