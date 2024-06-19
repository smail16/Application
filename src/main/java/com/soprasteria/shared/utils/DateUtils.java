package com.soprasteria.shared.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    public static LocalDate convertToLocalDate(Date dateToConvert) {
        return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static Date convertToDate(LocalDate localDateToConvert) {
        return Date.from(localDateToConvert.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static String formatUploadDate(Date dateToFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm", Locale.FRANCE);
        return formatter.format(dateToFormat);
    }
}
