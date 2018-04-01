package com.syzible.occupie.Common.Time;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateHelpers {

    public static String getIso8601Date(Date date) {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.UK);
        df.setTimeZone(tz);
        return df.format(date);
    }

    public static Date getDateFromIso8601(String string) {
        return null;
    }
}
