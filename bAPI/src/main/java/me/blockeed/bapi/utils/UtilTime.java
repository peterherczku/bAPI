package me.blockeed.bapi.utils;

import java.util.concurrent.TimeUnit;

public class UtilTime {

    public static String getNiceTime(long duration) {
        if (duration==-1) return "Soha";

        long days = TimeUnit.MILLISECONDS.toDays(duration);
        long hours = TimeUnit.MILLISECONDS.toHours(duration)-TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(duration));
        long minutes = TimeUnit.MILLISECONDS.toMinutes(duration)-TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(duration));

        return days+"n "+hours+"ó "+minutes+"p";
    }

}
