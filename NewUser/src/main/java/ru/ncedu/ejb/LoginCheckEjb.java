package ru.ncedu.ejb;

import org.apache.commons.codec.digest.DigestUtils;
import ru.ncedu.bean.User;
import ru.ncedu.bean.UserManager;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Created by Pavel on 09.03.2016.
 */

@Stateless
public class LoginCheckEjb {
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
        if(isNull(user)){
            return "nullField";
        }else if(!isSameUserExist(user)){
            return "userNotExist";
        }else if(!isGoodPas(user)){
            return "wrongPassword";
        }
        return "success";
    }
}
