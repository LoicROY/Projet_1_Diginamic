package fr.diginamic;

import fr.diginamic.service.CsvService;
import fr.diginamic.service.EntityManagerFactoryService;


public class IntegrationOpenFoodFacts {
    private static final String PATH = "C:/Users/yviqu/Desktop/Projet1_Diginamic/open-food-facts.csv";

    public static void main(String[] args) {

        try {
            CsvService.TransformCsvIntoProductList(PATH);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            EntityManagerFactoryService.getInstance().close();
        }
    }
}
