package ru.ncedu.bean.converters;

import ru.ncedu.bean.ProvidersB;
import ru.ncedu.service.MarketService;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Created by Павел on 29.03.2016.
 */
@FacesConverter(forClass = ProvidersB.class, value = "ProviderConverter")
public class ProviderConv implements Converter{
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if (s == null || s.isEmpty()) {
            return null;
        }
        return new ProvidersB(MarketService.getProviderById((Integer.parseInt(s))));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if (o == null || !(o instanceof ProvidersB)) {
            return null;
        }
        return String.valueOf(((ProvidersB) o).getProviderId());
    }
}
