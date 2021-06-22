package fr.diginamic.service;

import fr.diginamic.entities.*;

import java.util.Collection;

public class FilterCollectionService {

    public static boolean checkIfAlreadyInList(Collection<? extends NamedEntity> list, String string){
        for (NamedEntity entity : list) {
            if (entity.getNameAttribute().equalsIgnoreCase(string)){
                return true;
            }
        }
        return false;
    }

}
