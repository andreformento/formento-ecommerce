package com.formento.ecommerce.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class LocalDateUtil {

    private static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static LocalDate fromText(String text, DateTimeFormatter formatter) {
        return LocalDate.parse(text, formatter);
    }

    public static LocalDate fromText(String text) {
        return fromText(text, FORMATTER);
    }

    public static String format(LocalDate localDate, DateTimeFormatter formatter) {
        return localDate.format(formatter);
    }

    public static String format(LocalDate localDate, String formatterPattern) {
        return localDate.format(DateTimeFormatter.ofPattern(formatterPattern));
    }

    public static String format(LocalDate localDate) {
        return format(localDate, FORMATTER);
    }

    public static Date toDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate asLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

}
