package com.gensitive.utils;

import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {

    public static int getMonth() {
        Calendar rightNow = Calendar.getInstance();
        return rightNow.get(Calendar.MONTH) + 1;
    }

    public static int getYear() {
        Calendar rightNow = Calendar.getInstance();
        return rightNow.get(Calendar.YEAR);
    }

    public static int getLastDayInMonth(int year, int month) {
        Calendar calendar = new GregorianCalendar(year, month, 1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public static int getLastDayInThisMonth() {
        Calendar rightNow = Calendar.getInstance();
        return rightNow.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public static Date getLastDateInThisMonth() {
        Calendar rightNow = Calendar.getInstance();
        int year = rightNow.get(Calendar.YEAR);
        int month = rightNow.get(Calendar.MONTH);
        int day = rightNow.getActualMaximum(Calendar.DAY_OF_MONTH);

        Calendar calendar = new GregorianCalendar(year, month, day);
        return calendar.getTime();
    }

    public static Date getFirstDateInThisMonth() {
        Calendar rightNow = Calendar.getInstance();
        int year = rightNow.get(Calendar.YEAR);
        int month = rightNow.get(Calendar.MONTH);
        int day = 1;

        Calendar calendar = new GregorianCalendar(year, month, day);
        return calendar.getTime();
    }

    public static Date getLastDateInMonth(int year, int month) {
        int day = new GregorianCalendar(year, month, 1).getActualMaximum(Calendar.DAY_OF_MONTH);

        Calendar calendar = new GregorianCalendar(year, month, day);
        return calendar.getTime();
    }

    public static Date getFirstDateInMonth(int year, int month) {
        Calendar calendar = new GregorianCalendar(year, month, 1);
        return calendar.getTime();
    }

    public static int getWeekdayOfFirstDayInMonth(int year, int month) {
        Calendar calendar = new GregorianCalendar(year, month, 1);
        int weekDay = calendar.get(Calendar.DAY_OF_WEEK) - 2;
        if (weekDay == -1) {
            weekDay = 6;
        }
        return weekDay;
    }

    public static int getWeekdayOfFirstDayInThisMonth() {
        Calendar rightNow = Calendar.getInstance();
        int year = rightNow.get(Calendar.YEAR);
        int month = rightNow.get(Calendar.MONTH);
        int day = 1;

        Calendar calendar = new GregorianCalendar(year, month, day);
        int weekDay = calendar.get(Calendar.DAY_OF_WEEK) - 2;

        if (weekDay == -1) {
            weekDay = 6;
        }

        return weekDay;
    }

    public static String getMonthName(int month) {
        Calendar calendar = new GregorianCalendar(2019, month, 1);
        return calendar.getDisplayName(Calendar.MONTH, Calendar.LONG_STANDALONE, new Locale("fi"));
    }

    public static String getThisMonthName() {
        Calendar rightNow = Calendar.getInstance();
        int month = rightNow.get(Calendar.MONTH);
        return (getMonthName(month));
    }

    public static List<Integer> getWeeksOfThisMonth() {
        Calendar rightNow = Calendar.getInstance();
        int year = rightNow.get(Calendar.YEAR);
        int month = rightNow.get(Calendar.MONTH);
        return getWeeksOfMonth(year, month);
    }

    public static List<Integer> getWeeksOfMonth(int year, int month) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.set(Calendar.YEAR, year);
        rightNow.set(Calendar.MONTH, month);
        rightNow.set(Calendar.DAY_OF_MONTH, 1);

        List<Integer> weeks = new ArrayList<Integer>();
        int ndays = rightNow.getActualMaximum(Calendar.DAY_OF_MONTH);
        int oldWeek = 0;

        for (int i = 0; i < ndays; i++) {
            int newWeek = rightNow.get(Calendar.WEEK_OF_YEAR);
            if (oldWeek != newWeek) {
                weeks.add(newWeek);
                oldWeek = newWeek;
            }
            rightNow.add(Calendar.DATE, 1);
        }

        return weeks;
    }

    public static Date getDate(int year, int month, int day) {
        Calendar calendar = new GregorianCalendar(year, month - 1, day);
        return calendar.getTime();
    }
}
