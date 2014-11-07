/****FIRST PROJECT ABOUT RAP CREATE BY PAOLO *******/


package model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entity.*;
public class EquipmentModel {
    EntityManager em = ConnectData.getEntityManager();
    
    //Get Device by version
    public List<Device> getDeviceByVersion(int version){
       TypedQuery<Device> query = em.createQuery( "Select d.name, d.appModule, d.deviceType,d.physicalLocation,d.manufacturer From Device d Where d.versionBean.version = :version", Device.class);
        query.setParameter("version", version);
       List<Device> listDevice = query.getResultList();
       return listDevice;
    }
    
    // Get properties of version 
    public Version getPropertiesOfVersion(String version) {
        Query query = em.createQuery(
                "Select v from Version v Where v.version = :version",
                Object[].class);
        query.setParameter("version", version);
        Version v = (Version) query.getSingleResult();
        return v;
    }
    
    // Edit entity 
    public void editEntity(Object entity) {
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
        em.close();
        
    }
    
    //add new entity
    public void addEntity(Object entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        em.close();
    }
    
    //delete entity
    
    //delete version
    public void deleteVersion(Version version) {
        em.getTransaction().begin();
        version = em.getReference(Version.class, version.getVersion());
        em.remove(version);
        em.getTransaction().commit();
        em.close();
    }

    //delete equipment
    public void deleteEquipment(Equipment equipment) {
        em.getTransaction().begin();
        equipment = em.getReference(Equipment.class, equipment.getIdEquipment());
        em.remove(equipment);
        em.getTransaction().commit();
        em.close();
    }

    //Get all devices
    public List<AppModule> getAllAppModule() {
//        em.getTransaction().begin();
        TypedQuery<AppModule> query =em.createNamedQuery("AppModule.findAll", AppModule.class);
        List<AppModule> devices = query.getResultList();
        em.close();
        return devices;
    }

//    public List<String> getAllVersionName() {
//        Query query = em.createQuery( "Select v.versionName from Version v");
//        List<String> versions = query.getResultList();
//      return versions;
//    }


//    public String[] getAllVersion() {
//        Query query = em.createQuery(
//                "Select v.versionName from Version v");
//        String[] versions = query.get
//        return versions;
//    }
    
}
