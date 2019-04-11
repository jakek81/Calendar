package com.gensitive.controller;

import com.gensitive.dto.DateDto;
import com.gensitive.dto.NoteDto;
import com.gensitive.model.MonthNotes;
import com.gensitive.model.Note;
import com.gensitive.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
public class CalendarController {

    @Autowired
    private CalendarService calendarService;

    @GetMapping("/monthNotes")
    @ResponseBody
    public MonthNotes monthNotes(@RequestParam int year, @RequestParam int month) {
        return calendarService.getMonthNotesInTimePeriod(year, month);
    }

    @GetMapping("/currentMonthNotes")
    @ResponseBody
    public MonthNotes currentMonthNotes() {
        return calendarService.getCurrentMonthNotes();
    }

    @GetMapping("/notes")
    @ResponseBody
    public List<NoteDto> getNotes(@RequestParam Date fromDate, @RequestParam Date toDate) {
        return calendarService.getNotes(fromDate, toDate);
    }

    @PostMapping("/notes")
    public ResponseEntity createNote(@RequestBody NoteDto noteDto) {
        calendarService.createNote(noteDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/notes")
    public void deleteNote(@RequestBody NoteDto noteDto) {
        calendarService.deleteNotes(noteDto);
    }
}
