package ru.ncedu.ejb;

import ru.ncedu.bean.MarketManager;
import ru.ncedu.bean.OrderBean;
import ru.ncedu.service.UserService;
import ru.ncedu.service.Validator;

import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Алёна
 */
@Stateless
//@Stateful
//@StatefulTimeout(value = 20, unit = TimeUnit.MINUTES)
public class AddOrderEjb implements Serializable {

    @Inject
    MarketManager marketManager;

    @Inject
    Validator validator;

    public String addOrder(ru.ncedu.bean.OrderBean orderBean, ru.ncedu.bean.User userBean) {
//        if (isNull(userBean)) {
//            return "UserNotExist";
//        } else if (!validator.isEmailValid(userBean.getEmail())) {
//            return "WrongEmail";
//        } else if (!validator.isPhoneValid(orderBean.getPhone())) {
//            return "WrongPhone";
//        } else {

        //------------проверки--------------

        if("".equals(orderBean.getUserNameOrder()) ||
                "".equals(orderBean.getPhone())    ||
                "".equals(userBean.getEmail())){
            FacesContext.getCurrentInstance().addMessage("reg:name", new FacesMessage(null, "Null fields"));
            return "Err";
        }else if (UserService.getUserByEmail(userBean.getEmail()) == null){
            //FC
            return "Err";
        }else if (!validator.isPhoneValid(orderBean.getPhone())){
            //FC
            return "Err";
        }
            orderBean.setDate(new Date());
            orderBean.setDiscount(0);
            marketManager.addOrder(orderBean, userBean);
            return "OrderIsCompleted";
    }
}
