package ru.ncedu.ejb;

import org.apache.commons.codec.digest.DigestUtils;
import ru.ncedu.bean.User;
import ru.ncedu.bean.UserManager;
import ru.ncedu.bean.UserStatus;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 * Created by Pavel on 09.03.2016.
 */

@Stateless
public class LoginCheckEjb {
    public String msg;

    public String check(String mas){
        mas ="";
        if (msg.equals("aaa")){
            FacesMessage fm = new FacesMessage("Field is good");
            FacesContext.getCurrentInstance().addMessage("Field is good",fm);
            mas = "good";
        }else {
            FacesMessage fm = new FacesMessage("Field not aaa");
            FacesContext.getCurrentInstance().addMessage("Field is bad",fm);
            mas = "bad";
        }

        return mas;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    @Inject
    UserManager um ;

    boolean isNull(User user){
            if ("".equals(user.getUserName())){
                return true;
            }else if("".equals(user.getPassword())){
                return true;
            }
        return false;}

    public boolean isSameUserExist(ru.ncedu.bean.User user){
            if(um.getUserByUserName(user.getUserName()) != null){
                return true;
            }
        return false;
    }


    public boolean isGoodPas(ru.ncedu.bean.User user){
        String pasToCheck = DigestUtils.md5Hex(user.getPassword());
        String pasInDB = um.getUserByUserName(user.getUserName()).getPassword();
        return pasInDB.equals(pasToCheck);
    }

    public String loginCheck(ru.ncedu.bean.User user){
        String res ="";
        if (isNull(user)){
            FacesContext.getCurrentInstance().addMessage("login:name", new FacesMessage("bad name", "Null fields"));
            res = "nullField";
            return res;
        }else if(!isSameUserExist(user) || !isGoodPas(user)){
            FacesContext.getCurrentInstance().addMessage("login:name", new FacesMessage("user not exist", "Login or password error"));
            res = "Err";
            return res;
        }
        res = "success";

        UserStatus.setStatus(user);
        return res;
    }
}
