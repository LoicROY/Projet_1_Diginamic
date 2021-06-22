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

    //Integer for check and remove
    private static final int NUMBER_OF_VALUES_WAITED = 31;
    private static final int FIRST_ELEMENT = 0;

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

    public static List<Produit> TransformCsvIntoProductList(String path)
            throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {

        //Lecture du fichier
        List<String> stringList = readCsvFile(path);

        return createAndPersistProductListFromStringList(stringList);
    }

    private static List<String> readCsvFile(String path) throws IOException {
        Path pathFile = Paths.get(path);
        return Files.readAllLines(pathFile, StandardCharsets.UTF_8);
    }

    private static List<Produit> createAndPersistProductListFromStringList(List<String> stringList)
            throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {

        stringList.remove(FIRST_ELEMENT);
        List<Produit> productList = new ArrayList<>();

        for (String string : stringList) {
            //Split les lignes en tableau suivant le séparateur |
            String[] array = string.trim().split(SEPARATOR_FOR_SPLIT_CSV_TO_STRINGS, -1);

            //Check que le tableau contient bien 30 valeurs
            if (!checkAttributesNumberOk(array)){
                continue;
            }

            Produit produit = transformArrayToProduct(array);
            System.out.println(produit); //TODO comment this for production
            persistProduct(produit);
            productList.add(produit);
            break;
        }
        return productList;
    }

    private static boolean checkAttributesNumberOk(String[] array) {
        return array.length == NUMBER_OF_VALUES_WAITED;
    }

    private static Produit transformArrayToProduct(String[] array)
            throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {

        Produit produit = new Produit(
                array[NOM_INDEX].trim(),
                NutritionGradeFr.valueOf(array[NUTRITION_GRADE_FR_INDEX].trim().toUpperCase()),
                ParseService.parseFloat(array[ENERGIE_100G_INDEX].trim()),
                ParseService.parseFloat(array[GRAISSE_100G_INDEX].trim()),
                ParseService.parseFloat(array[SUCRE_100G_INDEX].trim()),
                ParseService.parseFloat(array[FIBRE_100G_INDEX].trim()),
                ParseService.parseFloat(array[PROTEINE_100G_INDEX].trim()),
                ParseService.parseFloat(array[SEL_100G_INDEX].trim()),
                ParseService.parseFloat(array[VIT_A_100G_INDEX].trim()),
                ParseService.parseFloat(array[VIT_D_100G_INDEX].trim()),
                ParseService.parseFloat(array[VIT_E_100G_INDEX].trim()),
                ParseService.parseFloat(array[VIT_K_100G_INDEX].trim()),
                ParseService.parseFloat(array[VIT_C_100G_INDEX].trim()),
                ParseService.parseFloat(array[VIT_B1_100G_INDEX].trim()),
                ParseService.parseFloat(array[VIT_B2_100G_INDEX].trim()),
                ParseService.parseFloat(array[VIT_PP_100G_INDEX].trim()),
                ParseService.parseFloat(array[VIT_B6_100G_INDEX].trim()),
                ParseService.parseFloat(array[VIT_B9_100G_INDEX].trim()),
                ParseService.parseFloat(array[VIT_B12_100G_INDEX].trim()),
                ParseService.parseFloat(array[CALCIUM_100G_INDEX].trim()),
                ParseService.parseFloat(array[MAGNESIUM_100G_INDEX].trim()),
                ParseService.parseFloat(array[IRON_100G_INDEX].trim()),
                ParseService.parseFloat(array[FER_100G_INDEX].trim()),
                ParseService.parseFloat(array[BETA_CAROTENE_100G_INDEX].trim()),
                ParseService.parseBoolean(array[PRESENCE_HUILE_DE_PALME_INDEX].trim())
        );
        addAssociation(produit, array);

        return produit;
    }

    private static void addAssociation(Produit produit, String[] array)
            throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {

        //Association OneToMany
        produit.setCategorie(
                QueryService.getIfExistOnDatabaseOrCreate(
                        StringService.resizeIfStringToMuchLonger(
                                StringService.cleanString(array[CATEGORIE_INDEX])),
                        Categorie.class));

        produit.setMarque(
                QueryService.getIfExistOnDatabaseOrCreate(
                        StringService.resizeIfStringToMuchLonger(
                                StringService.cleanString(array[MARQUE_INDEX])),
                        Marque.class));

        //Association ManyToMany
        produit.setIngredients(getObjectListFromString(array[INGREDIENTS_INDEX], Ingredient.class));
        produit.setAllergenes(getObjectListFromString(array[ALLERGENES_INDEX], Allergene.class));
        produit.setAdditifs(getObjectListFromString(array[ADDITIFS_INDEX], Additif.class));
    }

    private static <T extends NamedEntity> Set<T> getObjectListFromString(String stringOfProduct, Class<T> type)
            throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {

        Set<T> objects = new HashSet<>();

        //Split la string en tableau de string représentant les objets
        String[] objectsOnStringFormat = stringOfProduct.trim().split(SEPARATOR_FOR_SPLIT_STRING_TO_OBJECT);

        for (String objectOnStringFormat : objectsOnStringFormat) {
            objectOnStringFormat = StringService.cleanString(objectOnStringFormat); //Enleve les caractères spéciaux de la string
            if (objectOnStringFormat.isBlank()) {
                continue;
            }
            objectOnStringFormat = StringService.resizeIfStringToMuchLonger(objectOnStringFormat);
            if (FilterCollectionService.checkIfAlreadyInList(objects, objectOnStringFormat)) {
                continue;
            }
            T object= QueryService.getIfExistOnDatabaseOrCreate(objectOnStringFormat, type);
            objects.add(object);
        }
        return objects;
    }

    private static void persistProduct(Produit produit) {
        ENTITY_MANAGER.getTransaction().begin();
        ENTITY_MANAGER.persist(produit);
        ENTITY_MANAGER.getTransaction().commit();
    }

}
