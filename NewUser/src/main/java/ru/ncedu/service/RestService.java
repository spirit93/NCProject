package ru.ncedu.service;
import ru.ncedu.entity.Products;
import javax.ejb.Stateless;
import javax.persistence.*;
import javax.ws.rs.*;

    /**
     * Created by 1 on 25.03.2016.
     * author Dan Smirnov
     */

    @Path("/Products")// Products????? todo
    @Stateless
    public class RestService {
        @PersistenceContext(unitName = "NCEDU")

        @GET
        @Produces("application/xml")
        public String getAllProductsById() {
            StringBuilder newDiv = new StringBuilder(" <div class = \"col-md-2\">\n <div class=\"panel panel-info\">\n <div class=\"panel-heading\">");
        /*TypedQuery<Products> query = em.createNamedQuery(Book.FIND_ALL, Book.class);
        Books books = new Books(query.getResultList());*/
            Products p = new Products("Iphone", 1, 1, 0, "src\\main\\webapp\\resources\\img\\iphone6.jpg");
            newDiv.append(p.getNameOfProduct());
            newDiv.append("</div>\n <div class=\"panel-body\">\n");
            newDiv.append(" <img src=\""+ p.getImage()  +"\" width=\"150px\"  class=\"img-thumbnail\" alt=\"" + p.getNameOfProduct() + "\"/><br/>\n");
            newDiv.append(" <p class=\"text-primary\">Price:1000$</p>\n" +
                    "                                            <a href=\"#modal\" role=\"button\" class=\"btn btn-primary btn-xs\" data-toggle=\"modal\">Buy</a>\n" +
                    "                                        </div>\n" +
                    "                                    </div>\n" +
                    "                                </div>\n");
            return newDiv.toString();

        }
    }


