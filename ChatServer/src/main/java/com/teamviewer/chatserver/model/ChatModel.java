package com.teamviewer.chatserver.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="chat")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class ChatModel {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int num;
    @Column
    private String roomId;
    @Column
    private String userId;
    @Column
    private String chatStr;
    @CreatedDate
    @Column
    private Date chatCreatedDate;


    public ChatModel(){

    }

    public ChatModel(String roomId,String userId,String chatStr){
        this.roomId=roomId;
        this.userId=userId;
        this.chatStr=chatStr;
    }
}
