package com.soongsil.sixfourfour.model;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Table
public class User {
    @Id//primary key
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;
    @Column
    private String name;
    @Column
    private String phone;

    public User(long id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public long getId() {
        return id;
    }



    public String getName() {
        return name;
    }



    public String getPhone() {
        return phone;
    }

    public void modify(String name, String phone){
        this.name = name;
        this.phone = phone;
    }

}
