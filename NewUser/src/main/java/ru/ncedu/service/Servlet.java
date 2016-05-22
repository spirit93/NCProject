package ru.ncedu.service;

import ru.ncedu.entity.Order;
import ru.ncedu.entity.Products;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

/**
 * Created by 1 on 25.03.2016.
 * author Dan Smirnov
 */

@WebServlet("/servlet/*")
public class Servlet extends HttpServlet {
    private boolean m_isSorted = false;
    static int sNumPanelsInRole = 6;


    private String getHTMLOneTypeProduct (Products product, int panelNum, int size, String prodType){
        StringBuilder newDiv = new StringBuilder("");
        if(panelNum % sNumPanelsInRole == 0){
            newDiv.append(" <div class = \"myblock-"+ prodType +"\" ");
            if(panelNum / sNumPanelsInRole != 0){
                newDiv.append("style=\"display: none;\"");
            }
            newDiv.append(">\n");
        }
        newDiv.append(" <div class = \"col-md-4\">\n <div   class=\"panel panel-info\">\n <div class=\"panel-heading\">");
        newDiv.append(product.getNameOfProduct());
        for (int j = 0; j<68 - product.getNameOfProduct().length();j++){
            newDiv.append("&nbsp");
        }


        newDiv.append(" </div>\n <div class=\"panel-body\">\n");
        File img = new File(PropertiesClass.getProperties("pathToGFImgFold")+
                product.getCategories().getNameOfCategory()+"\\"+product.getNameOfProduct()+".jpg");
//            if(!img.exists()){
//                newDiv.append(" <img style=\"height:200px\" src=\""+ "resources/img/defCategories/"
//                        + product.getCategories().getNameOfCategory()+".jpg" + "\"");
//            }else{
            newDiv.append(" <img style=\"height:200px\" src=\""+ product.getProductDetails().getPathToImg() + "\"");
//        }

        newDiv.append(" <img style=\"height:200px\" src=\""+ product.getProductDetails().getPathToImg() + "\"");
        newDiv.append("   class=\"img-thumbnail\" alt=\"" + product.getNameOfProduct() + "\"/><br/>\n");
        newDiv.append(" <p class=\"text-primary\">Price: " + product.getProductDetails().getPricePerUnit() + " rub</p>\n");
        newDiv.append(" <a href=\"#modal\" role=\"button\" class=\"btn btn-primary \" id = \"prodId-" +
                product.getProductsId()+"\"data-toggle=\"modal\">Buy</a>\n");
        newDiv.append(" </div>\n </div>\n </div>\n");
        String idProd = "prodId-" + product.getProductsId();
        String a = "<script>\n" +
                "$('#"+idProd+"').click(function() {" +
                "document.getElementById('formHid:hiddenV').value = (this.id)});" +
                "</script>";
        newDiv.append(a);
        if((panelNum % sNumPanelsInRole == sNumPanelsInRole - 1) && (size>sNumPanelsInRole)){
            int pageNum = panelNum / sNumPanelsInRole + 1;
            int amountOfPages = (size-1) / sNumPanelsInRole + 1;
            newDiv.append("<div align=\"center\">\n");
            newDiv.append("<input class=\"btn btn-default\" style=\"width: 80px\" type=\"button\" onclick=\"fnPrev(\'myblock-"+ prodType +"\')\" value=\"Back\"/>\n");
            newDiv.append("<span class=\"text-info\">" + pageNum +"/"+ amountOfPages + "</span>\n");
            newDiv.append("<input class=\"btn btn-default\" style=\"width: 80px\" type=\"button\" onclick=\"fnNext(\'myblock-"+ prodType +"\')\" value=\"Next\"/>\n </div>\n</div>\n");
        }
        return newDiv.toString();
    }

    private String getHTMLAllTypeProducts(String typeProd) {
        int i;
        StringBuilder result = new StringBuilder("");
        List<Products> productsList = MarketService.getAllCategoryProducts(typeProd);
        if(productsList!=null) {
            if(this.m_isSorted){
                productsList.sort(new Comparator<Products>() {
                    @Override
                    public int compare(Products o1, Products o2) {
                        return o1.getProductDetails().getPricePerUnit() - o2.getProductDetails().getPricePerUnit();
                    }
                });
            }
            for (i=0; i < productsList.size(); i++) {
                if(productsList.get(i).getProductDetails().getAmountOfProducts()>0) {
                    result.append(getHTMLOneTypeProduct(productsList.get(i), i, productsList.size(), typeProd));
                }
            }
            if(((i-1) % sNumPanelsInRole != sNumPanelsInRole - 1) && (i>sNumPanelsInRole)){
                int pageNum = (i-1) / sNumPanelsInRole + 1;
                result.append("<div class=\"col-md-12\">\n<div align=\"center\">\n");
                result.append("<input class=\"btn btn-default\" style=\"width: 80px\" type=\"button\" onclick=\"fnPrev(\'myblock-"+ typeProd +"\')\" value=\"Back\"/>\n");
                result.append("<span class=\"text-info\">" + pageNum + "/" + pageNum + "</span>\n");
                result.append("<input class=\"btn btn-default disabled\" style=\"width: 80px\" type=\"button\" onclick=\"fnNext(\'myblock-"+ typeProd +"\')\" value=\"Next\"/>\n </div>\n</div>\n</div>\n");
            }
            if(i>sNumPanelsInRole){
                result.append("</div>\n");
            }
        }
        return result.toString();
    }

    private static String getHTMLOneOrder(Order order, String prodName){
        StringBuilder result = new StringBuilder("<tr>\n <td>"+ order.getIdOfProd() +"</td>\n");
        result.append("<td>").append(prodName).append("</td>\n");
        result.append("<td>").append(order.getPhone()).append("</td>\n");
        result.append("<td>").append(order.getUserNameOrder()).append("</td>\n");
        result.append("<td>").append(order.getStatus()).append("  ");
        //result.append("<input onclick=\"status(\'").append(order.getOrderId())
               // .append("\', \'1\')\" class=\"btn btn-default\" style=\"width:50px\" type=\"submit\" value=\"1\"/>");
        //result.append("<input onclick=\"status(\'").append(order.getOrderId())
                //.append("\', \'2\')\" class=\"btn btn-default\" style=\"width:50px\" type=\"submit\" value=\"2\"/>");
        result.append("<img src=\"resources\\img\\ok.png\" alt=\"ok\" onclick=\"status(\'").append(order.getOrderId())
                .append("\', \'1\')\" class=\"btn btn-default\" width=\"35px\"/>");
        result.append("<img src=\"resources\\img\\cross.png\" alt=\"ok\" onclick=\"status(\'").append(order.getOrderId())
                .append("\', \'2\')\" class=\"btn btn-default\" width=\"35px\"/>");
        result.append("</td>\n");
        result.append("</tr>\n");
        return result.toString();
    }

    static String getHTMLOrders(){
        StringBuilder result = new StringBuilder("");
        List<Order> ordersList = MarketService.getAllOrders();
        List<Products> products = MarketService.getAllOrderedProducts();
        if(ordersList!=null){
            result.append("<table class=\"table table-bordered\">\n <thead>\n <tr>");
            result.append("<th>Product id</th>\n <th>Product name</th>\n <th>Customer phone number</th>\n <th>Customer name</th>\n <th>Order status</th>\n </tr>\n </thead>");
            result.append("<tbody>\n");
            for(int i=0; i<ordersList.size(); i++){
                result.append(getHTMLOneOrder(ordersList.get(i), products.get(i).getNameOfProduct()));
            }
            result.append("</tbody>\n  </table>\n");
        }
        return result.toString();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String result="";
        String request = req.getParameter("type");
        this.m_isSorted = Boolean.valueOf(req.getParameter("sort"));
        if(request.equals("orders")){
            result = getHTMLOrders();
        }else{
            result = getHTMLAllTypeProducts(request);
        }
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(result);
    }


}


