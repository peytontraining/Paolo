/****FIRST PROJECT ABOUT RAP CREATE BY PAOLO *******/

package model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectData {
    private static final String PERSISTENCE_UNIT_NAME = "RAP-FirstProject";

    private static EntityManagerFactory factory  = Persistence
    .createEntityManagerFactory(PERSISTENCE_UNIT_NAME);    
    
    public static EntityManager getEntityManager() {        
        return factory.createEntityManager();
    }
}


