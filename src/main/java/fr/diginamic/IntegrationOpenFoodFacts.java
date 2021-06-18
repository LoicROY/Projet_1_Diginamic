package fr.diginamic;

import fr.diginamic.entities.*;
import fr.diginamic.service.CsvService;
import fr.diginamic.service.EntityManagerFactoryService;

public class IntegrationOpenFoodFacts {
    private static final String PATH = "C:/Users/yviqu/Desktop/Projet1_Diginamic/open-food-facts.csv";

    public static void main(String[] args) {

        try {
            CsvService.TransformCsvIntoObject(PATH);
//            System.out.println(QueryService.getIfExistOnDatabaseOrCreate("marque", Marque.class));
//            System.out.println(QueryService.getIfExistOnDatabaseOrCreate("categorie", Categorie.class));
//            System.out.println(QueryService.getIfExistOnDatabaseOrCreate("allergene", Allergene.class));
//            System.out.println(QueryService.getIfExistOnDatabaseOrCreate("additif", Additif.class));
//            System.out.println(QueryService.getIfExistOnDatabaseOrCreate("ingredient", Ingredient.class));

//            Set<NamedEntity> additifs = new HashSet<>();
//            Additif additif = new Additif("additif");
//            additifs.add(additif);
//            System.out.println(FilterSetService.checkIfAlreadyInList(additifs, additif));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            EntityManagerFactoryService.getInstance().close();
        }
    }
}
