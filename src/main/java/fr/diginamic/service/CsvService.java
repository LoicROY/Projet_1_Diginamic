package fr.diginamic.service;

import fr.diginamic.entities.*;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CsvService {

    private static final EntityManager ENTITY_MANAGER = EntityManagerFactoryService.getInstance().getEntityManager();

    //REGEX for split and replace
    private static final String SEPARATOR_FOR_SPLIT_CSV_TO_STRINGS = "\\|";
    private static final String SEPARATOR_FOR_SPLIT_STRING_TO_OBJECT = "[,;]";
    private static final String OLD_REGEX = "[\\*\\_\\(\\)\\<\\>\\.]";
    private static final String NEW_STRING = "";

    //Integer for check and remove
    private static final int NUMBER_OF_VALUES_WAITED = 31;
    private static final int FIRST_ELEMENT = 0;
    private static final int VARCHAR_MAX_LENGTH = 255;

    //Index for produit attributes
    private static final int CATEGORIE_INDEX = 0;
    private static final int MARQUE_INDEX = 1;
    private static final int NOM_INDEX = 2;
    private static final int NUTRITION_GRADE_FR_INDEX = 3;
    private static final int INGREDIENTS_INDEX = 4;
    private static final int ENERGIE_100G_INDEX = 5;
    private static final int GRAISSE_100G_INDEX = 6;
    private static final int SUCRE_100G_INDEX = 7;
    private static final int FIBRE_100G_INDEX = 8;
    private static final int PROTEINE_100G_INDEX = 9;
    private static final int SEL_100G_INDEX = 10;
    private static final int VIT_A_100G_INDEX = 11;
    private static final int VIT_D_100G_INDEX = 12;
    private static final int VIT_E_100G_INDEX = 13;
    private static final int VIT_K_100G_INDEX = 14;
    private static final int VIT_C_100G_INDEX = 15;
    private static final int VIT_B1_100G_INDEX = 16;
    private static final int VIT_B2_100G_INDEX = 17;
    private static final int VIT_PP_100G_INDEX = 18;
    private static final int VIT_B6_100G_INDEX = 19;
    private static final int VIT_B9_100G_INDEX = 20;
    private static final int VIT_B12_100G_INDEX = 21;
    private static final int CALCIUM_100G_INDEX = 22;
    private static final int MAGNESIUM_100G_INDEX = 23;
    private static final int IRON_100G_INDEX = 24;
    private static final int FER_100G_INDEX = 25;
    private static final int BETA_CAROTENE_100G_INDEX = 26;
    private static final int PRESENCE_HUILE_DE_PALME_INDEX = 27;
    private static final int ALLERGENES_INDEX = 28;
    private static final int ADDITIFS_INDEX = 29;

    public static Set<Produit> TransformCsvIntoObject(String path)
            throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {

        //Lecture du fichier
        List<String> stringList = readCsvFile(path);

        //Split les lignes en tableau suivant le séparateur |
        List<String[]> arrayList = splitStringListToArrayList(stringList);
        arrayList.remove(FIRST_ELEMENT);

        //Check que tous les tableaux ont bien 30 valeurs (enleve le tableau si check fail)
        checkArrayList(arrayList);

        Set<Produit> produits = new HashSet<>();
        //Transforme les tableaux en objet
        for (String[] array : arrayList) {
            Produit produit = transformArrayToProduct(array);
            System.out.println(produit);
            persistProduct(produit);
            produits.add(produit);
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

    private static void checkArrayList(List<String[]> arrayList) {
        arrayList.removeIf(array -> array.length != NUMBER_OF_VALUES_WAITED);
    }

    private static Produit transformArrayToProduct(String[] array)
            throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {

        Produit produit = new Produit(
                array[NOM_INDEX],
                NutritionGradeFr.valueOf(array[NUTRITION_GRADE_FR_INDEX].toUpperCase()),
                ParseService.parseFloat(array[ENERGIE_100G_INDEX]),
                ParseService.parseFloat(array[GRAISSE_100G_INDEX]),
                ParseService.parseFloat(array[SUCRE_100G_INDEX]),
                ParseService.parseFloat(array[FIBRE_100G_INDEX]),
                ParseService.parseFloat(array[PROTEINE_100G_INDEX]),
                ParseService.parseFloat(array[SEL_100G_INDEX]),
                ParseService.parseFloat(array[VIT_A_100G_INDEX]),
                ParseService.parseFloat(array[VIT_D_100G_INDEX]),
                ParseService.parseFloat(array[VIT_E_100G_INDEX]),
                ParseService.parseFloat(array[VIT_K_100G_INDEX]),
                ParseService.parseFloat(array[VIT_C_100G_INDEX]),
                ParseService.parseFloat(array[VIT_B1_100G_INDEX]),
                ParseService.parseFloat(array[VIT_B2_100G_INDEX]),
                ParseService.parseFloat(array[VIT_PP_100G_INDEX]),
                ParseService.parseFloat(array[VIT_B6_100G_INDEX]),
                ParseService.parseFloat(array[VIT_B9_100G_INDEX]),
                ParseService.parseFloat(array[VIT_B12_100G_INDEX]),
                ParseService.parseFloat(array[CALCIUM_100G_INDEX]),
                ParseService.parseFloat(array[MAGNESIUM_100G_INDEX]),
                ParseService.parseFloat(array[IRON_100G_INDEX]),
                ParseService.parseFloat(array[FER_100G_INDEX]),
                ParseService.parseFloat(array[BETA_CAROTENE_100G_INDEX]),
                ParseService.parseBoolean(array[PRESENCE_HUILE_DE_PALME_INDEX])
        );
        addAssociation(produit, array);

        return produit;
    }

    private static void addAssociation(Produit produit, String[] array)
            throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {

        //Association OneToMany
        produit.setCategorie(
                QueryService.getIfExistOnDatabaseOrCreate(
                        cleanString(array[CATEGORIE_INDEX]), Categorie.class));
        produit.setMarque(
                QueryService.getIfExistOnDatabaseOrCreate(
                        cleanString(array[MARQUE_INDEX]), Marque.class));

        //Association ManyToMany
        produit.setIngredients(getObjectListFromString(array[INGREDIENTS_INDEX], Ingredient.class));
        produit.setAllergenes(getObjectListFromString(array[ALLERGENES_INDEX], Allergene.class));
        produit.setAdditifs(getObjectListFromString(array[ADDITIFS_INDEX], Additif.class));
    }

    private static <T extends NamedEntity> Set<T> getObjectListFromString(String stringOfProduct, Class<T> type)
            throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {

        Set<T> objects = new HashSet<>();
        String[] objectsOnStringFormat = stringOfProduct.trim().split(SEPARATOR_FOR_SPLIT_STRING_TO_OBJECT);
        for (String objectOnStringFormat : objectsOnStringFormat) {
            objectOnStringFormat = cleanString(objectOnStringFormat);
            if (objectOnStringFormat.isBlank()) {
                continue;
            }
            if (objectOnStringFormat.length() > VARCHAR_MAX_LENGTH) {
                objectOnStringFormat = objectOnStringFormat.substring(0, VARCHAR_MAX_LENGTH - 1);
            }
            T object= QueryService.getIfExistOnDatabaseOrCreate(objectOnStringFormat, type);
            if (!FilterSetService.checkIfAlreadyInList(objects, object)) {
                objects.add(object);
            }
        }
        return objects;
    }

    private static String cleanString(String string) {
        return string.replaceAll(OLD_REGEX, NEW_STRING).trim();
    }

    private static void persistProduct(Produit produit) {
        ENTITY_MANAGER.getTransaction().begin();
        ENTITY_MANAGER.persist(produit);
        ENTITY_MANAGER.getTransaction().commit();
    }

}
