package com.gensitive.model;

import java.util.ArrayList;
import java.util.List;

public class NoteJson {

    private final List notes;
    private final List weeks;
    private final int firstDay;
    private final int numberOfDays;
    private final String monthName;
    private final int month;
    private final int year;

    public NoteJson(List notes, List weeks, int firstDay, int numberOfDays, String monthName, int month, int year) {
        this.notes = new ArrayList(notes);
        this.weeks = new ArrayList(weeks);
        this.firstDay = firstDay;
        this.numberOfDays = numberOfDays;
        this.monthName = monthName;
        this.month = month;
        this.year = year;
    }

    public List getNotes() {
        return notes;
    }

    public int getFirstDay() {
        return firstDay;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public String getMonthName() {
        return monthName;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public List getWeeks() {
        return weeks;
    }

}
