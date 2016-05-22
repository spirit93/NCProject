package ru.ncedu.ejb;

import ru.ncedu.bean.User;
import ru.ncedu.bean.UserManager;
import ru.ncedu.bean.UserStatus;
import ru.ncedu.service.UserService;
import ru.ncedu.service.Validator;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
        if (!isNotNull(user)){
            FacesContext.getCurrentInstance().addMessage("reg:name", new FacesMessage(null, "Null fields"));
            return "Err";
        }else if(!isSameUser(user.getUserName())){
            FacesContext.getCurrentInstance().addMessage("reg:name", new FacesMessage("", "Same user exists"));
            return "Err";
        }else if(!isSamePass(user)){
            FacesContext.getCurrentInstance().addMessage("reg:pas", new FacesMessage("", "Password error"));
            return "Err";
        }else if(!validator.isEmailValid(user.getEmail())){
            FacesContext.getCurrentInstance().addMessage("reg:email", new FacesMessage("", "Wrong email"));
            return "Err";
        }
        if(userManager.getAllUsers().isEmpty()){
            userManager.addUser(user,1);
        }else{
        userManager.addUser(user,0);
        }
        return "success";
    }

    public void changeStatus(User user,int status){
        userManager.changeUserStatus(user,status);
    }

    public String getUserStatus(){
        User user = UserStatus.getUser();
        if (user == null){
            return "user";
        }

        int i = UserService.getUserByName(user.getUserName()).getUserType().getType();

        if (i == 1){
            return "admin";
        }
        return "user";
    }
}
