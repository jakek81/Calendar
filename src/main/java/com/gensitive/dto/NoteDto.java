package com.gensitive.dto;

import java.sql.Date;

public class NoteDto {
    private String note;
    private Date noteDate;

    public NoteDto() {

    }

    public NoteDto(String note, Date noteDate) {
        this.note = note;
        this.noteDate = noteDate;
    }

    public Date getNoteDate() {
        return noteDate;
    }

    public void setNoteDate(Date noteDate) {
        this.noteDate = noteDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
