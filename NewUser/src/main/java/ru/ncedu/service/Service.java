package ru.ncedu.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;

/**
 * Created by Павел on 23.03.2016.
 */
@Stateless
public class Service {
    @PersistenceUnit(unitName="NCEDU")
    public static EntityManager em = Persistence.createEntityManagerFactory("NCEDU").createEntityManager();

}
