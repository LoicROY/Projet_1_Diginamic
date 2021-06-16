package fr.diginamic;

import fr.diginamic.entities.*;
import fr.diginamic.service.CsvService;
import java.util.Set;

public class IntegrationOpenFoodFacts {
    private static final String PATH = "C:/Users/yviqu/Desktop/Projet1_Diginamic/open-food-facts.csv";

    public static void main(String[] args) {

        try {
            CsvService.TransformCsvIntoObject(PATH);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
