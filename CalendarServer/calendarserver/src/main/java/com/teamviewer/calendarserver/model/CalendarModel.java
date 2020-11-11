package com.teamviewer.calendarserver.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="calendar")
@Getter
@Setter
public class CalendarModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int num;

    @Column
    private String roomId;

    @Column
    private String calName;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date calStart;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date calEnd;
}
