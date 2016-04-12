package ru.ncedu.service;

import ru.ncedu.ejb.AddCategories;
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

    private String getHTMLOneTypeProduct (Products product){
        StringBuilder newDiv = new StringBuilder(" <div class = \"col-md-2\">\n <div class=\"panel panel-info\">\n <div class=\"panel-heading\">");
        newDiv.append(product.getNameOfProduct());
        newDiv.append(" </div>\n <div class=\"panel-body\">\n");
        newDiv.append(" <img src=\""+ product.getProductDetails().getPathToImg() + "\"");
        newDiv.append(" width=\"150px\"  class=\"img-thumbnail\" alt=\"" + product.getNameOfProduct() + "\"/><br/>\n");
        newDiv.append(" <p class=\"text-primary\">Price: " + product.getProductDetails().getPricePerUnit() + "$</p>\n");
        newDiv.append(" <a href=\"#modal\" role=\"button\" class=\"btn btn-primary btn-xs\" data-toggle=\"modal\">Buy</a>\n");
        newDiv.append(" </div>\n </div>\n </div>\n");
        return newDiv.toString();
    }

    private String getHTMLAllTypeProducts(String typeProd) {
        StringBuilder result = new StringBuilder("");
        List<Products> productsList = MarketService.getAllCategoryProducts(typeProd);
        if(productsList!=null) {
            for (int i = 0; i < productsList.size(); i++) {
                result.append(getHTMLOneTypeProduct(productsList.get(i)));
            }
        }
        return result.toString();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String result = getHTMLAllTypeProducts(req.getParameter("type"));
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(result);
    }


}


