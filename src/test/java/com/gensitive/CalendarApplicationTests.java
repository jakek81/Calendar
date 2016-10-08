package com.gensitive;

import com.gensitive.dao.NoteDAO;
import com.gensitive.model.Note;
import com.gensitive.utils.DateUtil;
import java.util.Date;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
    public void testDatabaseReturnValue() throws Exception {
        Note note;
        Date date = DateUtil.getDate(2016, 12, 24);
        note = noteDAO.findByNoteDate(date);
        assertEquals("Jouluaatto", note.getNote());
    }

    @Test
    public void restNoParamReturnMessage() throws Exception {

        this.mockMvc.perform(get("/rest")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.monthName").exists());
    }

    @Test
    public void restParamReturnMessage() throws Exception {

        this.mockMvc.perform(get("/rest").param("day", "201609"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.monthName").value("Syyskuu"));
    }

}
