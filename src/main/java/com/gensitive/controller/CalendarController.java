package com.gensitive.controller;

import com.gensitive.dao.NoteDAO;
import com.gensitive.model.Note;
import com.gensitive.model.NoteJson;
import com.gensitive.utils.DateUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalendarController {

    @Autowired
    private NoteDAO noteDAO;

    @RequestMapping("/rest")
    @ResponseBody
    public NoteJson rest(@RequestParam(value = "month", defaultValue = "current") String day) {
        List<Note> noteList;

        if (day.equals("current")) {
            try {
                noteList = noteDAO.findByNoteDateBetween(DateUtil.getFirstDateInThisMonth(), DateUtil.getLastDateInThisMonth());
            } catch (Exception ex) {
                System.out.println("Exeption: " + ex);
                return null;
            }
            return new NoteJson(noteList,
                    DateUtil.getWeeksOfThisMonth(),
                    DateUtil.getWeekdayOfFirstDayInThisMonth(),
                    DateUtil.getLastDayInThisMonth(),
                    DateUtil.getThisMonthName(),
                    DateUtil.getMonth(),
                    DateUtil.getYear());
        } else {
            int year;
            int month;

            if (day.length() != 6) {
                return null;
            }

            try {
                year = Integer.parseInt(day.substring(0, 4));
                month = Integer.parseInt(day.substring(4, 6));
            } catch (Exception ex) {
                System.out.println("Exeption: " + ex);
                return null;
            }

            if (Math.abs(year - 2016) > 20 || month < 1 || month > 12) {
                return null;
            }
            month--;

            try {
                noteList = noteDAO.findByNoteDateBetween(DateUtil.getFirstDateInMonth(year, month), DateUtil.getLastDateInMonth(year, month));
            } catch (Exception ex) {
                System.out.println("Exeption: " + ex);
                return null;
            }
            return new NoteJson(noteList,
                    DateUtil.getWeeksOfMonth(year, month),
                    DateUtil.getWeekdayOfFirstDayInMonth(year, month),
                    DateUtil.getLastDayInMonth(year, month),
                    DateUtil.getMonthName(month),
                    month + 1,
                    year);
        }
    }
}
