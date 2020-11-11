package com.teamviewer.calendarserver.model.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OutputCalendarModel {
    private int num;
    private String calName;
    private String calStart;
    private String calEnd;
}
