package fr.diginamic.service;

import fr.diginamic.entities.NamedEntity;

import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.NoResultException;
import java.lang.reflect.InvocationTargetException;

public class QueryService {

    private static final EntityManager ENTITY_MANAGER = EntityManagerFactoryService.getInstance().getEntityManager();
    private static final String PARAMETER = "nom";


    public static <T extends NamedEntity> T getIfExistOnDatabaseOrCreate(String nom, Class<T> type) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        try {
            return ENTITY_MANAGER.createNamedQuery(type.getAnnotation(NamedQuery.class).name(), type)
                    .setParameter(PARAMETER, nom)
                    .getSingleResult();
        } catch (NoResultException e) {
            return type.getConstructor(new Class[] {String.class}).newInstance(nom);
        }
    }
}
