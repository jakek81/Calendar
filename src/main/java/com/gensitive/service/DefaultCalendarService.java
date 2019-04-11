package com.gensitive.service;

import com.gensitive.dao.NoteDAO;
import com.gensitive.dto.NoteDto;
import com.gensitive.model.MonthNotes;
import com.gensitive.model.Note;
import com.gensitive.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class DefaultCalendarService implements CalendarService {

    @Autowired
    private NoteDAO noteDAO;

    @Override
    public MonthNotes getCurrentMonthNotes() {
        return new MonthNotes(noteDAO.findByNoteDateBetween(DateUtil.getFirstDateInThisMonth(), DateUtil.getLastDateInThisMonth()),
                DateUtil.getWeeksOfThisMonth(),
                DateUtil.getWeekdayOfFirstDayInThisMonth(),
                DateUtil.getLastDayInThisMonth(),
                DateUtil.getThisMonthName(),
                DateUtil.getMonth(),
                DateUtil.getYear());
    }

    @Override
    public MonthNotes getMonthNotesInTimePeriod(int year, int month) {
        month--;

        return new MonthNotes(noteDAO.findByNoteDateBetween(DateUtil.getFirstDateInMonth(year, month), DateUtil.getLastDateInMonth(year, month)),
                DateUtil.getWeeksOfMonth(year, month),
                DateUtil.getWeekdayOfFirstDayInMonth(year, month),
                DateUtil.getLastDayInMonth(year, month),
                DateUtil.getMonthName(month),
                month + 1,
                year);
    }

    @Override
    public void deleteNotes(NoteDto noteDto) {
        List<Note> notes = noteDAO.findByNoteAndNoteDate(noteDto.getNote(), noteDto.getNoteDate());
        notes.stream().forEach(note -> noteDAO.delete(note));
    }

    @Override
    public void createNote(NoteDto noteDto) {
        Note note = new Note();
        note.setNote(noteDto.getNote());
        note.setNoteDate(noteDto.getNoteDate());
        noteDAO.save(note);
    }

    @Override
    public List<NoteDto> getNotes(Date fromDate, Date toDate) {
        List<NoteDto> noteDtos = new ArrayList<>();
        noteDAO.findByNoteDateBetween(fromDate, toDate).stream().forEach(note -> noteDtos.add(new NoteDto(note.getNote(), note.getNoteDate())));
        return noteDtos;
    }
}
