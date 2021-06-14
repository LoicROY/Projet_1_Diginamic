package fr.diginamic;

import fr.diginamic.service.CsvService;
import fr.diginamic.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Set;

public class IntegrationOpenFoodFacts {
    private static final String PATH = "C:/Users/yviqu/Desktop/Projet1_Diginamic/open-food-facts.csv";

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("open-food-facts");
        EntityManager em = emf.createEntityManager();
        System.out.println("connected on db");

        try {
            Set<Produit> produits = CsvService.TransformCsvIntoObject(PATH);

            //<--------------->
            //test produit
            Produit produit = new Produit();
            produit.setMarque(new Marque("auchan"));
            produit.setCategorie(new Categorie("dessert"));
            Ingredient ingredient = new Ingredient("chocolat");
            produit.addIngredient(ingredient);


            em.getTransaction().begin();
            em.persist(produits);
            em.getTransaction().commit();


        } catch (Exception e) {
            e.printStackTrace();
        }

        em.close();
        emf.close();
    }
}
