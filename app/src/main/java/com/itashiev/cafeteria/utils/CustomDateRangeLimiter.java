package com.itashiev.cafeteria.utils;

import android.os.Parcel;
import android.support.annotation.NonNull;

import com.wdullaer.materialdatetimepicker.date.DateRangeLimiter;

import java.util.Calendar;


public class CustomDateRangeLimiter implements DateRangeLimiter {

    private Calendar calendar = Calendar.getInstance();

    public CustomDateRangeLimiter() {

    }

    @Override
    public int getMinYear() {
        return calendar.get(Calendar.YEAR);
    }

    @Override
    public int getMaxYear() {
        return calendar.get(Calendar.YEAR);
    }

    @NonNull
    @Override
    public Calendar getStartDate() {
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH));
        return calendar;
    }

    @NonNull
    @Override
    public Calendar getEndDate() {
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH));
        return calendar;
    }

    @Override
    public boolean isOutOfRange(int year, int month, int day) {
        return false;
    }

    @NonNull
    @Override
    public Calendar setToNearestDate(@NonNull Calendar day) {
        return day;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
