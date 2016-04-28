package ru.ncedu.service;

import ru.ncedu.ejb.AddCategories;
import ru.ncedu.entity.Order;
import ru.ncedu.entity.ProductDetails;
import ru.ncedu.entity.Products;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

/**
 * Created by 1 on 25.03.2016.
 * author Dan Smirnov
 */

@WebServlet("/servlet/*")
public class Servlet extends HttpServlet {

    private String getHTMLOneTypeProduct (Products product, int panelNum, String prodType){ //не сразу постранично
        StringBuilder newDiv = new StringBuilder(""); //запрос выводящий со статусом 0
        if(panelNum % 12 == 0){ //текущая страница, перенести на все вкладки и в main_page
            newDiv.append(" <div class = \"myblock-"+ prodType +"\" ");
            if(panelNum / 12 != 0){
                newDiv.append("style=\"display: none;\"");
            }
            newDiv.append(">\n");
        }
        newDiv.append(" <div class = \"col-md-2\">\n <div class=\"panel panel-info\">\n <div class=\"panel-heading\">");
        newDiv.append(product.getNameOfProduct());
        newDiv.append(" </div>\n <div class=\"panel-body\">\n");
        newDiv.append(" <img src=\""+ product.getProductDetails().getPathToImg() + "\"");
        newDiv.append(" style=\"width:150px; height:70px\"  class=\"img-thumbnail\" alt=\"" + product.getNameOfProduct() + "\"/><br/>\n");
        newDiv.append(" <p class=\"text-primary\">Price: " + product.getProductDetails().getPricePerUnit() + "$</p>\n");
        newDiv.append(" <a href=\"#modal\" role=\"button\" class=\"btn btn-primary btn-xs\" id = \"prodId-" +
                product.getProductsId()+"\"data-toggle=\"modal\">Buy</a>\n");
        newDiv.append(" </div>\n </div>\n </div>\n");
        String idProd = "prodId-" + product.getProductsId();
        String a = "<script>\n" +
                "$('#"+idProd+"').click(function() {" +
                "document.getElementById('formHid:hiddenV').value = (this.id)});" +
                "</script>";
        newDiv.append(a);
        if(panelNum % 12 == 11){
            int pageNum = panelNum / 12 + 1;
            newDiv.append("<div align=\"center\">\n");
            newDiv.append("<input class=\"btn btn-default\" style=\"width: 80px\" type=\"button\" onclick=\"fnPrev(\'myblock-"+ prodType +"\')\" value=\"Back\"/>\n");
            newDiv.append("<span class=\"text-info\">" + pageNum + "</span>\n");
            newDiv.append("<input class=\"btn btn-default\" style=\"width: 80px\" type=\"button\" onclick=\"fnNext(\'myblock-"+ prodType +"\')\" value=\"Next\"/>\n </div>\n</div>\n");
        }
        return newDiv.toString();
    }

    private String getHTMLAllTypeProducts(String typeProd) {
        int i;
        StringBuilder result = new StringBuilder("");
        List<Products> productsList = MarketService.getAllCategoryProducts(typeProd);
        if(productsList!=null) {
            for (i=0; i < productsList.size(); i++) {
                result.append(getHTMLOneTypeProduct(productsList.get(i), i, typeProd));
            }
            if((i-1) % 12 != 11){
                int pageNum = (i-1) / 12 + 1;
                result.append("<div align=\"center\">\n");
                result.append("<input class=\"btn btn-default\" style=\"width: 80px\" type=\"button\" onclick=\"fnPrev(\'myblock-"+ typeProd +"\')\" value=\"Back\"/>\n");
                result.append("<span class=\"text-info\">" + pageNum + "</span>\n");
                result.append("<input class=\"btn btn-default\" style=\"width: 80px\" type=\"button\" onclick=\"fnNext(\'myblock-"+ typeProd +"\')\" value=\"Next\"/>\n </div>\n </div>\n");
            }
        }
        return result.toString();
    }

    private String getHTMLOneOrder(Order order, String prodName){
        StringBuilder result = new StringBuilder("<tr>\n <td>"+ order.getIdOfProd() +"</td>\n");
        result.append("<td>"+ prodName +"</td>\n");
        result.append("<td>"+ order.getPhone() +"</td>\n");
        result.append("<td>"+ order.getUserNameOrder() +"</td>\n");
        result.append("</tr>\n");
        return result.toString();
    }

    private String getHTMLOrders(){
        StringBuilder result = new StringBuilder("");
        List<Order> ordersList = MarketService.getAllOrders();
        List<Products> products = MarketService.getAllOrderedProducts();
        if(ordersList!=null){
            result.append("<table class=\"table table-bordered\">\n <thead>\n <tr>");
            result.append("<th>Product id</th>\n <th>Product name</th>\n <th>Customer phone number</th>\n <th>Customer name</th>\n </tr>\n </thead>");
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


