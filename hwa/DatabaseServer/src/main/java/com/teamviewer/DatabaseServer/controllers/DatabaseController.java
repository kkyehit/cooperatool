package com.teamviewer.DatabaseServer.controllers;


import com.teamviewer.DatabaseServer.model.request.DatabaseRequest;
import com.teamviewer.DatabaseServer.services.DatabaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RequestMapping("/db")
@RestController
@Slf4j
public class DatabaseController {

    @Autowired
    DatabaseService dbservice;

    //select
    @RequestMapping(value = "/select", method = RequestMethod.POST)
    public List<Map<String, String>> getselectquery(@RequestBody DatabaseRequest queryRequestModel) throws SQLException {
        log.info("Get " + queryRequestModel.getRoomId() + "'s query : " + queryRequestModel.getQuery());
        return dbservice.select(queryRequestModel);
    }


    //Not select
    @RequestMapping(value = "/others", method = RequestMethod.POST)
    public String getothersquery(@RequestBody DatabaseRequest queryRequestModel) throws SQLException {
        log.info("Get " + queryRequestModel.getRoomId() + "'s query : " + queryRequestModel.getQuery());
        return dbservice.others(queryRequestModel);
    }

}