package com.teamviewer.DatabaseServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class DatabaseServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatabaseServerApplication.class, args);

    }




}
