package br.com.devspring.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class LocalDateTimeUtils {

    private static String TIMEZONE = "America/Sao_Paulo";

    private static String FORMATODATA = "yyyy-MM-dd'T'HH:mm:ss";

    private LocalDateTimeUtils() {
    }

    public static Date converterStringParaDate(String data){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try {
            return sdf.parse(data);
        } catch (ParseException e) {
            Calendar c = Calendar.getInstance();
            Date dataAtual = c.getTime();
            return dataAtual;
        }
    }

    public static LocalDateTime converterStringParaLocalDateTime(String data) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATODATA);
            return LocalDateTime.parse(data, formatter.withZone(getZoneId()));
        } catch (Exception e) {
            try {
                return LocalDateTime.parse(data, DateTimeFormatter.ISO_DATE_TIME);
            } catch (Exception e1) {
                // Não implementado para retornar data atual
            }
            return dataAtual();
        }
    }

    public static String converterLocalDateTimeParaString(LocalDateTime localDateTime) {
        String formattedDateTime = "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATODATA);
        try {
            formattedDateTime = localDateTime.format(formatter);
        } catch (Exception e) {
            formattedDateTime = LocalDateTime.now().format(formatter);
        }
        return formattedDateTime;
    }

    public static LocalDateTime dataAtual() {
        return LocalDateTime.ofInstant(Instant.now(), getZoneId());
    }

    private static ZoneId getZoneId() {
        return TimeZone.getTimeZone(TIMEZONE).toZoneId();
    }

    public static String converterLongParaString(Long currentTimeMillis) {
        String formattedDateTime = "";
        SimpleDateFormat formatter = new SimpleDateFormat(FORMATODATA);
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern(FORMATODATA);
        try {
            formattedDateTime = formatter.format(currentTimeMillis);
        } catch (Exception e) {
            formattedDateTime = LocalDateTime.now().format(formatter1);
        }
        return formattedDateTime;
    }

}
