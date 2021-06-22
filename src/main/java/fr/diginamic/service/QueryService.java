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
            // 1ere solution
//            return ENTITY_MANAGER.createNamedQuery(type.getAnnotation(NamedQuery.class).name(), type)
//                    .setParameter(PARAMETER, nom)
//                    .getSingleResult();

            // 2eme solution
            return ENTITY_MANAGER.createQuery(getFindByNomQuery(type), type)
                    .setParameter(PARAMETER, nom)
                    .getSingleResult();

            // 3eme solution
//            NamedQuery query = null;
//            for (NamedQuery namedQuery : type.getAnnotationsByType(NamedQuery.class)) {
//                if (namedQuery.name().equals(type.getSimpleName() + ".findByNom")){
//                    query = namedQuery;
//                }
//            }
//            if (query == null) {
//                throw new AssertionError();
//            }
//            return ENTITY_MANAGER.createNamedQuery(query.name(), type)
//                    .setParameter(PARAMETER, nom)
//                    .getSingleResult();
        } catch (NoResultException e) {
            return type.getConstructor(new Class[] {String.class}).newInstance(nom);
        }
    }

    private static String getFindByNomQuery(Class<? extends NamedEntity> type){
        return String.format("SELECT alias FROM %s alias WHERE alias.nom = :nom", type.getSimpleName());
    }
}
