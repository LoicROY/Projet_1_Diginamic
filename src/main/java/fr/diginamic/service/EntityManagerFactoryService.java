package fr.diginamic.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactoryService {

    private static EntityManagerFactoryService instance;
    private EntityManagerFactory entityManagerFactory;

    private EntityManagerFactoryService(){
        connectToDataBase();
    }

    //Singleton
    public static EntityManagerFactoryService getInstance(){
        if (instance == null){
            instance = new EntityManagerFactoryService();
        }
        return instance;
    }

    public EntityManager getEntityManager(){
        if (!this.entityManagerFactory.isOpen()){
            connectToDataBase();
        }
        return this.entityManagerFactory.createEntityManager();
    }

    private void connectToDataBase(){
        this.entityManagerFactory = Persistence.createEntityManagerFactory("open-food-facts");
    }

    public void close(){
        this.entityManagerFactory.close();
    }
}
