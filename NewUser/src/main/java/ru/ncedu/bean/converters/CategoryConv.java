package ru.ncedu.bean.converters;

import ru.ncedu.bean.CategoriesB;
import ru.ncedu.bean.ProvidersB;
import ru.ncedu.service.MarketService;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Created by Павел on 30.03.2016.
 */
@FacesConverter(forClass = CategoriesB.class, value = "CategoryConverter")
public class CategoryConv implements Converter {


    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {

        if (s == null || s.isEmpty()) {
            return null;
        }

        return new CategoriesB(MarketService.getCategoryById((Integer.parseInt(s))));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if (o == null || !(o instanceof CategoriesB)) {
            return null;
        }
        return String.valueOf(((CategoriesB) o).getCategoryId());
    }



//
//    @FacesConverter(forClass = Author.class, value = "AuthorConverter")
//    public class AuthorConverter implements Converter {
//        public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
//            if (s == null || s.isEmpty()) {
//                return null;
//            }
//            return new Author(LibraryService.getAuthorById(Integer.parseInt(s)));
//        }
//
//        public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
//            if (o == null || !(o instanceof Author)) {
//                return null;
//            }
//            return String.valueOf(((Author) o).getId());
//        }
}
