package fr.diginamic;

import fr.diginamic.service.CsvService;
import fr.diginamic.entities.*;
import fr.diginamic.service.EntityManagerFactoryService;

import javax.persistence.EntityManager;
import java.util.Set;

public class IntegrationOpenFoodFacts {
    private static final String PATH = "C:/Users/yviqu/Desktop/Projet1_Diginamic/open-food-facts.csv";

    public static void main(String[] args) {
        EntityManagerFactoryService emf = EntityManagerFactoryService.getInstance();
        EntityManager em = emf.getEntityManager();
        System.out.println("connected on db");

        try {
            Set<Produit> produits = CsvService.TransformCsvIntoObject(PATH);
            em.getTransaction().begin();

//            for (Produit produit : produits){
//                System.out.println(produit);
//                em.persist(produit);
//            }

            em.getTransaction().commit();


        } catch (Exception e) {
            e.printStackTrace();
        }

        em.close();
        emf.close();
    }
}
