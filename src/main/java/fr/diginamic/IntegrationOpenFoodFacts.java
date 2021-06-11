package fr.diginamic;

import fr.diginamic.entities.Category;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class IntegrationOpenFoodFacts {
    public static void main(String[] args) {
        EntityManagerFactory  emf = Persistence.createEntityManagerFactory("open-food-facts");
        EntityManager em = emf.createEntityManager() ;

        Category categoryTest = new Category("fruits");
        System.out.println(categoryTest);

        em.getTransaction().begin();
        em.persist(categoryTest);
        em.getTransaction().commit();

    }
}
