package br.com.itau.pix.util;

import lombok.extern.log4j.Log4j2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Log4j2
public class DateFormatUtil {

    public static String formatToString(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS");

        return localDateTime.format(formatter);
    }

    public static String formatToString(String dateTimeStr) {
        try{
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            return dateTime.format(formatter);
        }catch (Exception e){
            log.info("Error trying to format date: " + dateTimeStr);
        }
        return dateTimeStr;
    }
}
