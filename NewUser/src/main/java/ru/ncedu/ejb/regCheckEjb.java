package ru.ncedu.ejb;

import ru.ncedu.bean.User;
import ru.ncedu.bean.UserManager;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Created by Pavel on 29.02.2016.
 */
@Stateless
public class regCheckEjb {
    @Inject
    UserManager userManager;

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
            return "same user exist";
        }
        else  if (!isSamePass(user)){
            return "notSamePass";
        }
        userManager.addUser(user);
        return "success";
    }
}
