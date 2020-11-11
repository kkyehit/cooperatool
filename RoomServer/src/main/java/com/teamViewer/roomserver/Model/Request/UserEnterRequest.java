package com.teamViewer.roomserver.Model.Request;

public class UserEnterRequest {
    public String userId;
    public String roomId;
    public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserEnterRequest() {
    }

    public String getUserId() { return userId; }

    public String getRoomId() {
        return roomId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
}
