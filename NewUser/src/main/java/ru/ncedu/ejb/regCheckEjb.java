package ru.ncedu.ejb;

import ru.ncedu.bean.User;
import ru.ncedu.bean.UserManager;
import ru.ncedu.service.Validator;

import javax.ejb.Stateless;
import javax.inject.Inject;
//import javax.validation.constraints.Pattern;

/**
 * Created by Pavel on 29.02.2016.
 */
@Stateless
public class RegCheckEjb {
    @Inject
    UserManager userManager;
    @Inject
    Validator validator;

    boolean isSamePass(User user){
        return user.getPassword().equals(user.getRepassword());
    }

    boolean isNotNull(User user){
        return !"".equals(user.getUserName())
                && !"".equals(user.getPassword())
                && !"".equals(user.getRepassword())
                && !"".equals(user.getEmail());
    }

    boolean isSameUser(String userName){
        if (userName != null){
            if(userManager.getUserByUserName(userName) == null){
                return true;
            }
        }
        return false;
    }

    public String regUser(User user){
        if(!isNotNull(user)){
            return "nullField";
        }
        else  if(!isSameUser(user.getUserName())){
            return "sameUserExist";
        }
        else  if (!isSamePass(user)){
            return "notSamePass";
        }
        else if (!validator.isEmailValid(user.getEmail())){
            return "wrongEmail";
        }
        userManager.addUser(user);
        return "success";
    }
}
