package fr.diginamic.service;

import fr.diginamic.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class QueryService {

    private static final EntityManager ENTITY_MANAGER = EntityManagerFactoryService.getInstance().getEntityManager();
    public static final String NAMED_QUERY_ADDITIF = "additif.findByNom";
    public static final String NAMED_QUERY_ALLERGENE = "allergene.findByNom";
    public static final String NAMED_QUERY_CATEGORIE = "categorie.findByNom";
    public static final String NAMED_QUERY_INGREDIENT = "ingredient.findByNom";
    public static final String NAMED_QUERY_MARQUE = "marque.findByNom";
    private static final String PARAMETER = "nom";

    public static Categorie getCategorieIfExistOrCreate(String nom) {
        try {
            return ENTITY_MANAGER.createNamedQuery(NAMED_QUERY_CATEGORIE, Categorie.class)
                    .setParameter(PARAMETER, nom)
                    .getSingleResult();
        } catch (NoResultException e) {
            return new Categorie(nom);
        }
    }

    public static Marque getMarqueIfExistOrCreate(String nom) {
        try {
            return ENTITY_MANAGER.createNamedQuery(NAMED_QUERY_MARQUE, Marque.class)
                    .setParameter(PARAMETER, nom)
                    .getSingleResult();
        } catch (NoResultException e) {
            return new Marque(nom);
        }
    }

    public static Ingredient getIngredientIfExistOrCreate(String nom) {
        try {
            return ENTITY_MANAGER.createNamedQuery(NAMED_QUERY_INGREDIENT, Ingredient.class)
                    .setParameter(PARAMETER, nom)
                    .getSingleResult();
        } catch (NoResultException e) {
            return new Ingredient(nom);
        }
    }

    public static Allergene getAllergeneIfExistOrCreate(String nom) {
        try {
            return ENTITY_MANAGER.createNamedQuery(NAMED_QUERY_ALLERGENE, Allergene.class)
                    .setParameter(PARAMETER, nom)
                    .getSingleResult();
        } catch (NoResultException e) {
            return new Allergene(nom);
        }
    }

    public static Additif getAdditifIfExistOrCreate(String nom) {
        try {
            return ENTITY_MANAGER.createNamedQuery(NAMED_QUERY_ADDITIF, Additif.class)
                    .setParameter(PARAMETER, nom)
                    .getSingleResult();
        } catch (NoResultException e) {
            return new Additif(nom);
        }
    }
}
