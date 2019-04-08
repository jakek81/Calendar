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

import java.util.Date;
import java.util.List;

@RestController
public class CalendarController {

    @Autowired
    private CalendarService calendarService;

    @GetMapping("/monthNotes")
    @ResponseBody
    public MonthNotes monthNotes(@RequestParam(required = false) Integer year, @RequestParam(required = false) Integer month) {
        if (year == null || month == null) {
            return calendarService.getCurrentMonthNotes();
        } else {
            return calendarService.getMonthNotesInTimePeriod(year, month);
        }
    }

    @GetMapping("/notes")
    @ResponseBody
    public List<NoteDto> getNotes(@RequestBody DateDto dateDto) {
        return calendarService.getNotes(dateDto.getFromDate(), dateDto.getToDate());
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
