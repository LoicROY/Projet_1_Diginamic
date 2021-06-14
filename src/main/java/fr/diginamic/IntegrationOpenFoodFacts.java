package fr.diginamic;

import fr.diginamic.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class IntegrationOpenFoodFacts {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("open-food-facts");
        EntityManager em = emf.createEntityManager();
        System.out.println("connected on db");

        //<--------------->
        //test produit
        Produit produit = new Produit();
        produit.setMarque(new Marque("auchan"));
        produit.setCategorie(new Categorie("dessert"));
        Ingredient ingredient = new Ingredient("chocolat");
        Ingredient ingredient2 = new Ingredient("café");
        produit.addIngredient(ingredient);
        produit.addIngredient(ingredient2);

        //<--------------->
        //test categorie
//        Categorie categoryTest = new Categorie("fruits");
//        System.out.println(categoryTest);

        em.getTransaction().begin();
        em.persist(produit);
        em.getTransaction().commit();

        em.getTransaction().begin();
        produit.removeIngredient(ingredient);
        em.getTransaction().commit();

        em.close();
        emf.close();

    }
}
