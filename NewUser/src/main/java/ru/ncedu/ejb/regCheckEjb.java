package ru.ncedu.ejb;

import ru.ncedu.bean.User;
import ru.ncedu.bean.UserManager;
import ru.ncedu.service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Created by Павел on 29.02.2016.
 */
@Stateless
public class regCheckEjb {

    @Inject
    UserManager userManager;
    public ru.ncedu.bean.User getUserByUserName( String userName){

        ru.ncedu.entity.User us = UserService.getUserByName(userName);

        if (us != null){
            return new ru.ncedu.bean.User(us);
        }
        return null;
    }

    boolean samePass(User user){
        if (user.getPassword().equals(user.getRepassword())) {
            return true;
        }
        return false;
    }

    boolean notNull(User user){
        if (!user.getUserName().equals("")
                && !user.getPassword().equals("")
                && !user.getRepassword().equals("")
                && !user.getEmail().equals("")
                ) {
            return true;
        }
        return false;
    }

    boolean sameUser(String userName){
        if (userName != null){
            if(getUserByUserName(userName) == null){
                return true;
            }
        }
        return false;
    }

    public String regUser(User user){
        if(!notNull(user)){
            return "nullField";
        }
        else  if(!sameUser(user.getUserName())){
            return "same user exist";
        }
        else  if (!samePass(user)){
            return "notSamePass";
        }
        userManager.addUser(user);
        return "success";
    }


//     ------------     for tests    -------------
    public String regTestUser(String uN,String ps1,String ps2,String email){

        User user = new User(uN,ps1,ps2,email);
        if(!sameUser(user.getUserName())){
            return "same user exist";
        }else  if (!samePass(user)){
            return "notSamePass";
        }
        userManager.addUser(user);
        return "success";
    }
}
