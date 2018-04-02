package com.syzible.occupie.Common.Helpers;

import java.text.DateFormat;
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
        Date date = new Date(birthday);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.UK);
        return df.format(date);
    }

    public static Date getDateFromIso8601(String string) {
        return new Date(string);
    }
}
