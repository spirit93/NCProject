package ru.ncedu.service;

import ru.ncedu.bean.User;

import javax.ejb.Stateless;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Павел on 23.03.2016.
 */
@Stateless
public class Validator {

    public boolean isEmailValid(String email){
        if (email == null){
            return false;
        }
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*" +
                "@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*([0-9A-Za-z])$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean isPhoneValid(String phone){
        if (phone == null){
            return false;
        }
        Pattern pattern = Pattern.compile("^[0-9]+[0-9-]+[0-9]$");
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }
}
