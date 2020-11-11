package com.teamviewer.calendarserver.services;

import com.teamviewer.calendarserver.model.CalendarModel;
import com.teamviewer.calendarserver.model.output.OutputCalendarModel;
import com.teamviewer.calendarserver.repository.CalendarRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CalendarService {
    @Autowired
    private CalendarRepository calendarRepository;

    public void calCreate(CalendarModel calendarModel) {
        calendarRepository.save(calendarModel);
    }

    public List<OutputCalendarModel> getAllCal(String roomId) {
        //수정해야됨
        List<CalendarModel> calendarModels=calendarRepository.findByRoomId(roomId);
        List<OutputCalendarModel> outputCalendarModels=calendarModels.stream().map(calendarModel ->
        {return new OutputCalendarModel(calendarModel.getNum(),calendarModel.getCalName(),calendarModel.getCalStart(),
                calendarModel.getCalEnd());}).collect(Collectors.toList());
        return outputCalendarModels;
    }

    @Transactional
    public void calDelete(int num) {
        CalendarModel calendarModel=calendarRepository.findByNum(num);
        calendarRepository.delete(calendarModel);
    }
}
