package fr.diginamic;

import fr.diginamic.entities.NutritionGradeFr;
import fr.diginamic.entities.Produit;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class test {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("open-food-facts");
        EntityManager em = emf.createEntityManager();
        System.out.println("connected on db");
        em.getTransaction().begin();

       // Création d'un nouveau produit
        Produit produit1= new Produit();



        em.getTransaction().commit();
        em.close();
        emf.close();

    }
}
