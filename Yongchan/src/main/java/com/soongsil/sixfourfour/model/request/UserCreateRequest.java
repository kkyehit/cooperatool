package com.soongsil.sixfourfour.model.request;

public class UserCreateRequest {

    private String name;
    private String phone;


    public UserCreateRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
