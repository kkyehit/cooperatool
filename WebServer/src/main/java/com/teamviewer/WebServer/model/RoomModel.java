package com.teamviewer.WebServer.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomModel {
    private int roomNum;
    private String roomId;
    private String userId;
    private String name;

    @Builder
    public RoomModel(String _roomId, String _userId, String _name) {
        this.roomId = _roomId;
        this.userId = _userId;
        this.name = _name;
    }
}


