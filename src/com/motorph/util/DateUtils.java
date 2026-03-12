package com.motorph.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class DateUtils {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("H:mm");
    
    public static String dateToString(LocalDate date) {
        return (date == null) ? "" : date.format(FORMATTER);
    }
    
    public static LocalDate stringToDate(String dateStr) {
        return (dateStr == null || dateStr.isEmpty()) ? null : LocalDate.parse(dateStr, FORMATTER);
    }
    
    public static LocalTime stringToTime(String timeStr) {
        return (timeStr == null || timeStr.isEmpty()) ? null : LocalTime.parse(timeStr, FORMATTER);
    }

}
