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
        // Parse the date time string to LocalDateTime
        try{
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr);

            // Define the desired format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            // Format the LocalDateTime to the desired format

            return dateTime.format(formatter);
        }catch (Exception e){
            log.info("Erro ao formatar o date: " + dateTimeStr);
        }
        return dateTimeStr;
    }
}
