package ru.ncedu.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Created by Павел on 19.03.2016.
 */

@ManagedBean
@RequestScoped
public class ProductsB {
    public String nameOfPr;

    public ProductsB(){
    }

    public void setNameOfPr(String name){
        nameOfPr = name;
    }

}
