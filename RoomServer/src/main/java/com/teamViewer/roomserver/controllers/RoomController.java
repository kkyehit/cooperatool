package com.teamViewer.roomserver.controllers;

import com.teamViewer.roomserver.Exception.NoDataException;
import com.teamViewer.roomserver.Model.Request.*;
import com.teamViewer.roomserver.Model.RoomModel;
import com.teamViewer.roomserver.services.RoomService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/rooms")
@RestController
@ControllerAdvice
@Slf4j
public class RoomController {

    @Autowired
    RoomService roomService;

    RoomModel rooms;


    //UserJoinRequest받아서 방에 참가시켜주는 함수
   /*issue#7 추측가능한 url 제거*/
    @ResponseBody
    @RequestMapping(value = "/enter", method = RequestMethod.POST)
    public String join(@RequestBody UserEnterRequest enterRequestModel) throws Exception{
        log.info("Enter " + enterRequestModel.getName() + "to " + enterRequestModel.getRoomId());
        roomService.enterUser(enterRequestModel);
        return "ok";
    }

    //UserExitRequest받아서 방에서 퇴장시켜주는 함수
    @ResponseBody
    @RequestMapping(value = "/exit", method = RequestMethod.POST)
    public String exit(@RequestBody UserExitRequest exitRequestModel) throws NoDataException {
        log.info("Exit " + exitRequestModel.getName() + "to " + exitRequestModel.getRoomId());
        roomService.exitUser(exitRequestModel);
        return "ok";
    }



    //방 이름, 유저 ID 받아서 방 생성하고 방 ID 리턴해주는 기능
    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@RequestBody CreateRoomRequest createRequestModel){
        log.info("Post new Room [" +createRequestModel.getName()+"]");
        System.out.println("Post new Room [" +createRequestModel.getName()+"]");
        return roomService.createRoom(createRequestModel.getUserId(), createRequestModel.getName());
    }



    //userId받아서 userId가 속해있는 방의 목록 리턴해주는 함수
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public List<RoomModel> roomListById(@PathVariable("userId") String userId) throws NoDataException {
        log.debug("get ID");
        return roomService.getRoomList(userId);

    }

    //roomId받아서 room에 참가한 사용자의 목록 리턴해주는 함수
    @RequestMapping(value = "/{roomId}", method = RequestMethod.POST)
    public List<RoomModel> userListById(@RequestBody UserListRequest userListRequestModel) throws NoDataException {
        return roomService.getUserList(userListRequestModel.getRoomId());

    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        /*issue#4 sysout 대신 log4j사용*/
        /*@slf4j로 log*/
        log.debug("test");
        return "test result";
    }

    /*Exception Handler*/
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = NoDataException.class)
    public String userErrorHandler(Exception e) {
        /*issue#6  HTTP Status Code 활용*/
        return e.toString();
    }
}
