package fr.diginamic.service;

import fr.diginamic.entities.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CsvService {

    private static final String SEPARATOR_FOR_SPLIT_CSV_TO_STRINGS = "\\|";
    private static final String SEPARATOR_FOR_SPLIT_STRING_TO_OBJECT = ",|;";
    private static final int NUMBER_OF_VALUES_WAITED = 31;

    private static final int CATEGORIE = 0;
    private static final int MARQUE = 1;
    private static final int NOM = 2;
    private static final int NUTRITION_GRADE_FR = 3;
    private static final int INGREDIENTS = 4;
    private static final int ENERGIE_100G = 5;
    private static final int GRAISSE_100G = 6;
    private static final int SUCRE_100G = 7;
    private static final int FIBRE_100G = 8;
    private static final int PROTEINE_100G = 9;
    private static final int SEL_100G = 10;
    private static final int VIT_A_100G = 11;
    private static final int VIT_D_100G = 12;
    private static final int VIT_E_100G = 13;
    private static final int VIT_K_100G = 14;
    private static final int VIT_C_100G = 15;
    private static final int VIT_B1_100G = 16;
    private static final int VIT_B2_100G = 17;
    private static final int VIT_PP_100G = 18;
    private static final int VIT_B6_100G = 19;
    private static final int VIT_B9_100G= 20;
    private static final int VIT_B12_100G = 21;
    private static final int CALCIUM_100G= 22;
    private static final int MAGNESIUM_100G = 23;
    private static final int IRON_100G = 24;
    private static final int FER_100G = 25;
    private static final int BETA_CAROTENE_100G = 26;
    private static final int PRESENCE_HUILE_DE_PALME = 27;
    private static final int ALLERGENES = 28;
    private static final int ADDITIFS = 29;

    public static Set<Produit> TransformCsvIntoObject(String path) throws IOException, LengthException {
        //Lecture du fichier
        List<String> stringList = readCsvFile(path);

        //Split les lignes en tableau suivant le séparateur |
        List<String[]> arrayList = splitStringListToArrayList(stringList);

        //Check que tous les tableaux ont bien 30 valeurs (enleve le tableau si check fail)
        checkArrayList(arrayList);

        Set<Produit> produits = new HashSet<>();
        //Transforme les tableaux en objet
        for (String[] array : arrayList) {
        produits.add(transformArrayToObject(array));
        }

        return produits;
    }

    private static List<String> readCsvFile(String path) throws IOException {
        Path pathFile = Paths.get(path);
        return Files.readAllLines(pathFile, StandardCharsets.UTF_8);
    }

    private static List<String[]> splitStringListToArrayList(List<String> stringList) {
        List<String[]> arrayList = new ArrayList<>();

        for (String string : stringList) {
            String[] array = string.trim().split(SEPARATOR_FOR_SPLIT_CSV_TO_STRINGS, -1);
            arrayList.add(array);
        }
        return arrayList;
    }

    private static void checkArrayList(List<String[]> arrayList) throws LengthException {
        List<String[]> arrayCheckFailed = new ArrayList<>();

        for (Iterator<String[]> iterator = arrayList.iterator(); iterator.hasNext();){
            String[] array = iterator.next();
            if (!checkLength(array)){
                arrayCheckFailed.add(array);
                iterator.remove();
            }
        }
    }

    private static boolean checkLength(String[] array) throws LengthException {
        //            throw new LengthException(
        //                    String.format("Array length(%d) is different from the length waited(%d)",
        //                            array.length, NUMBER_OF_VALUES_WAITED));
        return array.length == NUMBER_OF_VALUES_WAITED;
    }

    private static Produit transformArrayToObject(String[] array){
        Produit produit = new Produit(
                array[NOM],
                NutritionGradeFr.valueOf(array[NUTRITION_GRADE_FR]),
                Float.parseFloat(array[ENERGIE_100G]),
                Float.parseFloat(array[GRAISSE_100G]),
                Float.parseFloat(array[SUCRE_100G]),
                Float.parseFloat(array[FIBRE_100G]),
                Float.parseFloat(array[PROTEINE_100G]),
                Float.parseFloat(array[SEL_100G]),
                Float.parseFloat(array[VIT_A_100G]),
                Float.parseFloat(array[VIT_D_100G]),
                Float.parseFloat(array[VIT_E_100G]),
                Float.parseFloat(array[VIT_K_100G]),
                Float.parseFloat(array[VIT_C_100G]),
                Float.parseFloat(array[VIT_B1_100G]),
                Float.parseFloat(array[VIT_B2_100G]),
                Float.parseFloat(array[VIT_PP_100G]),
                Float.parseFloat(array[VIT_B6_100G]),
                Float.parseFloat(array[VIT_B9_100G]),
                Float.parseFloat(array[VIT_B12_100G]),
                Float.parseFloat(array[CALCIUM_100G]),
                Float.parseFloat(array[MAGNESIUM_100G]),
                Float.parseFloat(array[IRON_100G]),
                Float.parseFloat(array[FER_100G]),
                Float.parseFloat(array[BETA_CAROTENE_100G]),
                Float.parseFloat(array[PRESENCE_HUILE_DE_PALME])
        );
        produit.setCategorie(new Categorie(array[CATEGORIE]));
        produit.setMarque(new Marque(array[MARQUE]));

        //Récupère un Set d'Ingredient depuis le string du csv
        Set<Ingredient> ingredients = new HashSet<>();
        String[] ingredientsOnStringFormat = array[INGREDIENTS].trim().split(SEPARATOR_FOR_SPLIT_STRING_TO_OBJECT);
        for (String ingredient : ingredientsOnStringFormat) {
            ingredients.add(new Ingredient(ingredient.trim()));
        }
        produit.setIngredients(ingredients);

        //Récupère un Set d'Allergene depuis le string du csv
        Set<Allergene> allergenes = new HashSet<>();
        String[] allergenesOnStringFormat = array[ALLERGENES].trim().split(SEPARATOR_FOR_SPLIT_STRING_TO_OBJECT);
        for (String allergene : allergenesOnStringFormat) {
            allergenes.add(new Allergene(allergene.trim()));
        }
        produit.setAllergenes(allergenes);

        //Récupère un Set d'Additif depuis le string du csv
        Set<Additif> additifs = new HashSet<>();
        String[] additifsOnStringFormat = array[INGREDIENTS].trim().split(SEPARATOR_FOR_SPLIT_STRING_TO_OBJECT);
        for (String additif : additifsOnStringFormat) {
            additifs.add(new Additif(additif.trim()));
        }
        produit.setAdditifs(additifs);

        return produit;
    }

}
