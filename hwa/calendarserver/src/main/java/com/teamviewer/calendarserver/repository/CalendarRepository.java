package com.teamviewer.calendarserver.repository;

import com.teamviewer.calendarserver.model.CalendarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalendarRepository extends JpaRepository<CalendarModel,Integer> {
    public List<CalendarModel> findByRoomId(String roomId);
    //public CalendarModel deleteByNum(int num);
    public CalendarModel findByNum(int num);
}
