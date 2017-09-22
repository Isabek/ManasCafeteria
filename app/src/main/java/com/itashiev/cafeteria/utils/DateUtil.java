package com.itashiev.cafeteria.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtil {
    private static SimpleDateFormat tabDateFormat = new SimpleDateFormat("dd MMM EEE");
    private static SimpleDateFormat apiDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static Date nextNDaysDate(Date from, int numberOfDays) {
        return new Date(from.getTime() + TimeUnit.DAYS.toMillis(numberOfDays));
    }

    public static String nextNDaysDateForTab(Date from, int numberOfDays) {
        Date date = nextNDaysDate(from, numberOfDays);
        return tabDateFormat.format(date);
    }

    public static String formatted(long date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        return apiDateFormat.format(calendar.getTime());
    }
}
