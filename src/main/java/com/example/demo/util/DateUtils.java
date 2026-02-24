package com.example.demo.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = 
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter DATE_FORMATTER = 
            DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TIME_FORMATTER = 
            DateTimeFormatter.ofPattern("HH:mm:ss");

    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime != null ? dateTime.format(DATE_TIME_FORMATTER) : "";
    }

    public static String formatDate(LocalDateTime date) {
        return date != null ? date.format(DATE_FORMATTER) : "";
    }

    public static String formatTime(LocalDateTime time) {
        return time != null ? time.format(TIME_FORMATTER) : "";
    }

    public static String getRelativeTime(LocalDateTime dateTime) {
        if (dateTime == null) {
            return "";
        }

        LocalDateTime now = LocalDateTime.now();
        long minutesDiff = java.time.temporal.ChronoUnit.MINUTES.between(dateTime, now);
        long hoursDiff = java.time.temporal.ChronoUnit.HOURS.between(dateTime, now);
        long daysDiff = java.time.temporal.ChronoUnit.DAYS.between(dateTime, now);

        if (minutesDiff < 1) {
            return "Just now";
        } else if (minutesDiff < 60) {
            return minutesDiff + " minute" + (minutesDiff > 1 ? "s" : "") + " ago";
        } else if (hoursDiff < 24) {
            return hoursDiff + " hour" + (hoursDiff > 1 ? "s" : "") + " ago";
        } else if (daysDiff < 30) {
            return daysDiff + " day" + (daysDiff > 1 ? "s" : "") + " ago";
        } else {
            return formatDateTime(dateTime);
        }
    }
}
