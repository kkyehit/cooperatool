package com.teamViewer.roomserver.Model.Request;

public class RoomListRequest {
    private String userId;
    private String roomId;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RoomListRequest() {
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
