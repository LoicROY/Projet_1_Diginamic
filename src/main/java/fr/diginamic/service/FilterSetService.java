package fr.diginamic.service;

import fr.diginamic.entities.*;

import java.util.Locale;
import java.util.Set;

public class FilterSetService {

    public static boolean checkIfIngredientAlreadyInList(Set<Ingredient> list, Ingredient ingredient){
        for (Ingredient i : list) {
            if (i.getNom().equalsIgnoreCase(ingredient.getNom())){
                return true;
            }
        }
        return false;
    }
    public static boolean checkIfAllergeneAlreadyInList(Set<Allergene> list, Allergene allergene){
        for (Allergene a : list) {
            if (a.getNom().equalsIgnoreCase(allergene.getNom())){
                return true;
            }
        }
        return false;
    }
    public static boolean checkIfAdditifAlreadyInList(Set<Additif> list, Additif additif){
        for (Additif a : list) {
            if (a.getNom().equalsIgnoreCase(additif.getNom())){
                return true;
            }
        }
        return false;
    }
}
