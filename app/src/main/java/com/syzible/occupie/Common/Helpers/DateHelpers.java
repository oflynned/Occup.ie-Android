package com.syzible.occupie.Common.Helpers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateHelpers {

    public static String getIso8601Date(Date date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.UK);
        return df.format(date);
    }

    public static String getBirthdayFormat(String birthday) {
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd", Locale.UK).parse(birthday);
            return DateFormat.getDateInstance(DateFormat.SHORT).format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.UK);
        return format.format(new Date(birthday));
    }

    public static Date getDateFromIso8601(String string) {
        return new Date(string);
    }
}
