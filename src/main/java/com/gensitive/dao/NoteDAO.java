package com.gensitive.dao;

import java.util.List;

import com.gensitive.model.Note;
import java.util.Date;
import org.springframework.data.repository.CrudRepository;

public interface NoteDAO extends CrudRepository<Note, Long> {

    public Note findById(int id);

    public List<Note> findByNoteDateBetween(Date fromDate, Date toDate);

    public List<Note> findByNoteDate(Date noteDate);

    public List<Note> findByNoteAndNoteDate(String note, Date noteDate);

    public Note save(Note note);

    public void delete(Note note);
}
