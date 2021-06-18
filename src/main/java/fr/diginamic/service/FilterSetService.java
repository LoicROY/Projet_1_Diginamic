package fr.diginamic.service;

import fr.diginamic.entities.*;
import java.util.Set;

public class FilterSetService {

    public static boolean checkIfAlreadyInList(Set<? extends NamedEntity> list, NamedEntity object){
        for (NamedEntity entity : list) {
            if (entity.getNom().equalsIgnoreCase(object.getNom())){
                return true;
            }
        }
        return false;
    }

}
