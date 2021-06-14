package fr.diginamic;

import fr.diginamic.service.CsvService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class IntegrationOpenFoodFacts {
    private static final String PATH = "C:/Users/yviqu/Desktop/Projet1_Diginamic/open-food-facts.csv";

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("open-food-facts");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            CsvService.TransformCsvIntoObject(PATH);

        } catch (Exception e) {
            e.printStackTrace();
        }

        entityManager.close();
        entityManagerFactory.close();
    }
}
