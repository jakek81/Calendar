package com.gensitive;

import com.gensitive.dao.NoteDAO;
import com.gensitive.dto.DateDto;
import com.gensitive.dto.NoteDto;
import com.gensitive.utils.DateUtil;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CalendarApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private NoteDAO noteDAO;

    @Test
    public void testCreateGetDeleteNote() throws Exception {
        Date date = new java.sql.Date(DateUtil.getDate(2019, 12, 24).getTime());
        NoteDto noteDto = new NoteDto("Jouluaatto", date);

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        String noteJson = gson.toJson(noteDto);

        DateDto dateDto = new DateDto(date, date);
        String dateJson = gson.toJson(dateDto);

        String test = date.toString();

        this.mockMvc.perform(post("/notes")
                .contentType(MediaType.APPLICATION_JSON).content(noteJson));

        this.mockMvc.perform(get("/notes")
                .param("fromDate", date.toString())
                .param("toDate", date.toString()))
                .andExpect(jsonPath("$[0].note")
                .value("Jouluaatto"));

        this.mockMvc.perform(delete("/notes")
                .contentType(MediaType.APPLICATION_JSON).content(noteJson));


        this.mockMvc.perform(get("/notes")
                .param("fromDate", date.toString())
                .param("toDate", date.toString()))
                .andExpect(jsonPath("$[0].note")
                .doesNotExist());

    }

    @Test
    public void testMonthNotes() throws Exception {
        Date date = new java.sql.Date(DateUtil.getDate(2019, 12, 24).getTime());
        NoteDto noteDto = new NoteDto("Jouluaatto", date);
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        String noteJson = gson.toJson(noteDto);

        this.mockMvc.perform(post("/notes")
                .contentType(MediaType.APPLICATION_JSON).content(noteJson));

        this.mockMvc.perform(get("/monthNotes")
                .param("year", "2019")
                .param("month", "12"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.notes[0].note")
                .value("Jouluaatto"));

        this.mockMvc.perform(delete("/notes")
                .contentType(MediaType.APPLICATION_JSON).content(noteJson));

        this.mockMvc.perform(get("/monthNotes")
                .param("year", "2019")
                .param("month", "12"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.notes[0].note")
                .doesNotExist());
    }

    @Test
    public void testMonthName() throws Exception {
        this.mockMvc.perform(get("/monthNotes").param("year", "2019").param("month", "9"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.monthName").value("Syyskuu"));
    }

    @Test
    public void testCurrentMonthName() throws Exception {
        this.mockMvc.perform(get("/monthNotes")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.monthName").exists());
    }
}
