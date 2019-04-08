package com.gensitive.model;

import com.gensitive.dto.NoteDto;

import java.util.ArrayList;
import java.util.List;

public class MonthNotes {

    private final List<NoteDto> notes;
    private final List<Integer> weeks;
    private final int firstDay;
    private final int numberOfDays;
    private final String monthName;
    private final int month;
    private final int year;

    public MonthNotes(List<Note> notes, List weeks, int firstDay, int numberOfDays, String monthName, int month, int year) {
        this.weeks = weeks;
        this.firstDay = firstDay;
        this.numberOfDays = numberOfDays;
        this.monthName = monthName;
        this.month = month;
        this.year = year;

        this.notes = new ArrayList<>();
        notes.stream().forEach(note -> this.notes.add(new NoteDto(note.getNote(), note.getNoteDate())));
    }

    public List<NoteDto> getNotes() {
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

    public List<Integer> getWeeks() {
        return weeks;
    }

}
