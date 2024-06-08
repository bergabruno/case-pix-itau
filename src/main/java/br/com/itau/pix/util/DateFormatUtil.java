package br.com.itau.pix.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateFormatUtil {

    public static String formatToString(LocalDateTime localDateTime) {
        // Define o formato do DateTimeFormatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS");

        // Parse a string para LocalDateTime
        return localDateTime.format(formatter);
    }
}
