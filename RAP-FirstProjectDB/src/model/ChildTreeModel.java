/****FIRST PROJECT ABOUT RAP CREATE BY PAOLO *******/

package model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entity.AppModule;
import entity.Type;

public class ChildTreeModel {
    EntityManager em = ConnectData.getEntityManager();
    
    //Get all type
    public List<Type> getAllType(){
        Query query = em.createNamedQuery("Type.findAll");
        return query.getResultList();
    }

    public List<AppModule> getAllAppModule() {
        Query query = em.createNamedQuery("AppModule.findAll");
        return query.getResultList();
        }
}
