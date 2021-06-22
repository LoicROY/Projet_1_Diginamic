package fr.diginamic.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactoryService {

    private static EntityManagerFactoryService instance;
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    private EntityManagerFactoryService() {
        connectToDataBase();
    }

    //Singleton
    public static EntityManagerFactoryService getInstance() {
        if (instance == null) {
            instance = new EntityManagerFactoryService();
        }
        return instance;
    }

    public EntityManager getEntityManager() {
        if (!this.entityManagerFactory.isOpen()) {
            connectToDataBase();
        }
        return this.entityManager;
    }

    private void connectToDataBase() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("test");
        this.entityManager = this.entityManagerFactory.createEntityManager();
        System.out.println("connected on db");
    }

    public void close() {
        if (this.entityManagerFactory.isOpen()) {
            if (this.entityManager.isOpen()) {
                this.entityManager.close();
            }
            this.entityManagerFactory.close();
        }
    }
}
