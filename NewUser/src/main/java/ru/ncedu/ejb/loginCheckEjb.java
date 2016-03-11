package ru.ncedu.ejb;

import ru.ncedu.bean.User;
import ru.ncedu.bean.UserManager;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.swing.text.StringContent;

/**
 * Created by Viktoriya on 09.03.2016.
 */

@Stateless
public class loginCheckEjb {
    @Inject
    UserManager um ;

    boolean notNull(User user){
        if (!user.getUserName().equals("")
                && !user.getPassword().equals("")
                ) {
            return true;
        }
        return false;
    }

    public boolean userExist(ru.ncedu.bean.User user){
        if(um.getUserByUserName(user.getUserName()) != null){
            return true;
        }
        return false;
    }

    public boolean goodPas(ru.ncedu.bean.User user){
        String pasInDB = um.getUserByUserName(user.getUserName()).getPassword();

        if(pasInDB.equals(user.getPassword())){
            return true;
        }
        return false;
    }

    public String loginCheck(ru.ncedu.bean.User user){
        if (!notNull(user)){
            return "nullField";
        }else if(!userExist(user)){
            return "userNotExist";
        }else if(!goodPas(user)){
            return "wrongPassword";
        }
        return "success";
    }
//    ----- for test ----
    public String loginCheck(String uN, String pas){
        ru.ncedu.bean.User user = new User();
        user.setUserName(uN);
        user.setPassword(pas);

//        if (!notNull(user)){
//            return "nullField";
//        }
//        else if (!userExist(user)){
//            return "userNotExist";
//        }
//        else if(!goodPas(user)){
//            return "wrongPassword";
//        }
        return "success";
    }
}
