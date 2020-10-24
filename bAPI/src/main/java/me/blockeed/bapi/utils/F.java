package me.blockeed.bapi.utils;

public class F {

    public static String notice(String message) {
        return "§eÉrtesítés §7• §r"+message;
    }

    public static String success(String message) {
        return "§6Sikeres §7• §r"+message;
    }

    public static String fail(String message) {
        return "§4Hiba §7• §r"+message;
    }

    public static String formatUppercase(String message) { return String.valueOf(message.charAt(0)).toUpperCase()+message.substring(1, message.length()).toLowerCase();}

}
