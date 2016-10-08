package com.gensitive.dao;

import java.util.List;

import com.gensitive.model.Note;
import java.util.Date;
import org.springframework.data.repository.CrudRepository;

public interface NoteDAO extends CrudRepository<Note, Long> {

    public List<Note> findByNoteDateBetween(Date fromDate, Date toDate);

    public Note findById(int id);

    public Note findByNoteDate(Date noteDate);

}
