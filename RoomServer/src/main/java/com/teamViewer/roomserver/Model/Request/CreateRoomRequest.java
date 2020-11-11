package com.teamViewer.roomserver.Model.Request;

public class CreateRoomRequest {
    private String userId;
    private String name;


    public CreateRoomRequest(){

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
