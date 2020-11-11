package com.teamviewer.calendarserver.controller;

import com.teamviewer.calendarserver.model.CalendarModel;
import com.teamviewer.calendarserver.model.output.OutputCalendarModel;
import com.teamviewer.calendarserver.services.CalendarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/calendar")
public class CalendarController {
    @Autowired
    private CalendarService calendarService;

    @PostMapping("/sch")
    public String addCalendar(@RequestBody CalendarModel calendarModel){
        calendarService.calCreate(calendarModel);
        log.debug("calendar add ok");
        return "ok";
    }

    @PostMapping("/sch/{num}")
    public String delCalendar(@PathVariable int num){
        calendarService.calDelete(num);
        log.debug("calendar delete ok");
        return "ok";
    }

    @GetMapping("/{roomId}")
    public List<OutputCalendarModel> getCalendar(@PathVariable String roomId){
        List<OutputCalendarModel> outputCalendarModels=calendarService.getAllCal(roomId);
        log.debug("calendar get ok");
        return outputCalendarModels;
    }

}
