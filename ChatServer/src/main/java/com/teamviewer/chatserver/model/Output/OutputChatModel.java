package com.teamviewer.chatserver.model.Output;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Getter
@Setter
public class OutputChatModel {
    private String userId;
    private String chatStr;
    private Date chatCreatedDate;

    public OutputChatModel(String userId, String chatStr, Date chatCreatedDate) {
        this.userId = userId;
        this.chatStr = chatStr;
        this.chatCreatedDate = chatCreatedDate;
    }
}
