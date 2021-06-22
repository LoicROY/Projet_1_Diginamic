package fr.diginamic.service;

public class StringService {
    private static final String OLD_REGEX = "[\\*\\_\\(\\)\\<\\>\\.]";
    private static final String NEW_STRING = "";
    private static final int VARCHAR_MAX_LENGTH = 255;


    public static String cleanString(String string) {
        return string.replaceAll(OLD_REGEX, NEW_STRING).trim();
    }

    public static String resizeIfStringToMuchLonger(String string) {
        return string.length() > VARCHAR_MAX_LENGTH ? string.substring(0, VARCHAR_MAX_LENGTH - 1) : string;
    }
}
