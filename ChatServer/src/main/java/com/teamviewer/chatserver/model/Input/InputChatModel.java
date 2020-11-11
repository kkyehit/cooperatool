package com.teamviewer.chatserver.model.Input;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class InputChatModel {
    private String roomId;
    private String userId;
    private String chatStr;
}
