package com.gensitive.service;

import com.gensitive.dto.NoteDto;
import com.gensitive.model.MonthNotes;
import com.gensitive.model.Note;

import java.util.Date;
import java.util.List;

public interface CalendarService {

    MonthNotes getCurrentMonthNotes();

    MonthNotes getMonthNotesInTimePeriod(int year, int month);

    void deleteNotes(NoteDto noteDto);

    void createNote(NoteDto noteDto);

    List<NoteDto> getNotes(Date fromDate, Date toDate);
}
