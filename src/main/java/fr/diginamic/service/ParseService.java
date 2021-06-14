package fr.diginamic.service;

public class ParseService {

    public static boolean parseBoolean(String string){
        return string.equals("1");
    }

    public static Float parseFloat(String string){
        if (string.isBlank()){
            return null;
        }
        return Float.parseFloat(string);
    }
}
