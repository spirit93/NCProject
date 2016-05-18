package ru.ncedu.ejb;

import org.primefaces.renderkit.InputRenderer;
import ru.ncedu.bean.*;
import ru.ncedu.entity.ProductDetails;
import ru.ncedu.entity.Products;
import ru.ncedu.service.MarketService;
import ru.ncedu.service.PropertiesClass;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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

//        if (MarketService.getProductByName(product.getNameOfProduct())==null){
//            return "err";
//        }

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

    public String addProdFromTxt(ProductsB product, ProdDetailsB detailsB){
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

        detailsB.setPathToImg(detailsB.getPathToImg());

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

    public void parseData(){
        String pathToTxt = "C:\\TV.txt";
        File list = new File(pathToTxt);
        String sCurrentLine;
        try {
            BufferedReader br  = new BufferedReader(new FileReader(list));
                while((sCurrentLine = br.readLine()) != null){
                    String[] prodPar = sCurrentLine.split(";");
                    String cat = "TV";
                    String prov = "";

//                    System.out.println(Arrays.toString(prodPar));
                    ProductsB prod = new ProductsB();
                    ProdDetailsB details = new ProdDetailsB();

                    prod.setNameOfProduct(prodPar[0]);
                    prod.setCategoryName(cat);

                    if (prodPar[2].equals("l")){
                        prov = "LG";
                    }else if (prodPar[2].equals("sa")){
                        prov = "samsung";
                    }else if (prodPar[2].equals("s")){
                        prov = "sony";
                    }else if (prodPar[2].equals("o")){
                        prov = "other";
                    }
                    prod.setProviderName(prov);

                    details.setPricePerUnit(Integer.parseInt(prodPar[1]));

                    Random random = new Random();

                    int max = 30;
                    int min = 15;

                    int amount = random.nextInt((max - min) + 1) + min;
                    details.setAmountOfProducts(amount);

                    String pathToImg = "resources/img/categories/" + cat + "/" + prodPar[0] +".jpg";
                    details.setPathToImg(pathToImg);

                    addProdFromTxt(prod,details);
                }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
