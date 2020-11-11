package com.teamviewer.WebServer.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.teamviewer.WebServer.client.BoardClient;
import com.teamviewer.WebServer.client.LoginClient;
import com.teamviewer.WebServer.client.RoomClient;
import com.teamviewer.WebServer.model.LoginRequestModel;
import com.teamviewer.WebServer.model.LoginResponseModel;
import com.teamviewer.WebServer.model.RoomModel;
import com.teamviewer.WebServer.session.UserInfo;
import jdk.internal.net.http.common.Log;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class pageController {

	public static final Map<String, HttpSession> sessionMap = new HashMap<>();

	@Autowired
	private LoginClient loginClient;
	@Autowired
	private RoomClient roomClient;
	@Autowired
	private BoardClient boardClient;

	@Resource
	private UserInfo userInfo;

	pageController(){
	}

	@RequestMapping("/login")
	public String loginPage(){
		log.debug("login");
		return "login2.html";
	}

	/*model을 이용하여 thymeleaf 사용*/
	@RequestMapping("/main")
	public String mainPage(Model model, @RequestParam("sessionId") String sessionId) throws Exception {
		log.info("main");
		log.info("session Id : "+sessionId);
		userInfo = (UserInfo) sessionMap.get(sessionId).getAttribute("userInfo");
		log.info(userInfo.getName());
		log.info(userInfo.getUserId());
		if(userInfo.getName() == null || userInfo.getUserId() == null) return "redirect:http://20.39.185.121:5555/webserver/login";
		userInfo.setRoomModelList(roomClient.roomListById(userInfo.getUserId()));
		model.addAttribute("name", userInfo.getName());
		model.addAttribute("userId", userInfo.getUserId());
		model.addAttribute("activatedRoom", (userInfo.getActivatedRoom() == null ) ? 0 : userInfo.getActivatedRoom());
		userInfo.setBoardList(boardClient.getBoardList((userInfo.getActivatedRoom() == null ) ? 0 : userInfo.getActivatedRoom()));
		model.addAttribute("roomList", userInfo.getRoomModelList());
		model.addAttribute("postList", userInfo.getBoardList());
		model.addAttribute("sessionId", sessionId);
		return "cootoo.html";
	}
	@RequestMapping("/main/{roomId}")
	public String mainPage(Model model, @PathVariable("roomId") Integer roomId) throws Exception {
		userInfo.setBoardList(boardClient.getBoardList(roomId));
		userInfo.setActivatedRoom(roomId);
		log.info("roomId");
		log.info("roomId " + roomId);
		log.info("user " + userInfo.getUserId());
		if(userInfo.getName() == null || userInfo.getUserId() == null) return "redirect:http://20.39.185.121:5555/webserver/login";
		model.addAttribute("name", userInfo.getName());
		model.addAttribute("userId", userInfo.getUserId());
		model.addAttribute("activatedRoom", (userInfo.getActivatedRoom() == null ) ? 0 : userInfo.getActivatedRoom());
		userInfo.setBoardList(boardClient.getBoardList((userInfo.getActivatedRoom() == null ) ? 0 : userInfo.getActivatedRoom()));
		model.addAttribute("roomList", userInfo.getRoomModelList());
		model.addAttribute("postList", userInfo.getBoardList());
		return "cootoo.html";
	}

	/**/
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String login(HttpServletRequest request){
		HttpSession session = request.getSession();
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		log.info("login.do");
		log.info("user Id : "+userId);
		try{
			LoginResponseModel loginResponseModel = loginClient.login(new LoginRequestModel(userId, userPw));
			userInfo.setUserId(loginResponseModel.getUserId());
			userInfo.setName(loginResponseModel.getName());
			session.setAttribute("userInfo",userInfo);
			log.info("session Id : "+session.getId());
			sessionMap.put(session.getId(), session);
		}catch (Exception e){
			log.info(e.toString());
			return "redirect:http://20.39.185.121:5555/webserver/login";
		}
		return "forward:/main?sessionId="+session.getId();
	}

	@RequestMapping(value = "/RoomList/{userId}", method = RequestMethod.GET)
	public String getRoomList(@PathVariable("userId") String userId, @RequestParam("sessionId") String sessionId) throws Exception {
		userInfo.setRoomModelList(roomClient.roomListById(userId));
		return "forward:/main";
	}
	@RequestMapping(value = "/PostsList/{roomId}", method = RequestMethod.GET)
	public String getPostList(@PathVariable("roomId") Integer roomId, @RequestParam("sessionId") String sessionId){
		userInfo = (UserInfo) sessionMap.get(sessionId).getAttribute("userInfo");
		userInfo.setBoardList(boardClient.getBoardList(roomId));
		userInfo.setActivatedRoom(roomId);
		sessionMap.get(sessionId).setAttribute("userInfo", userInfo);
		log.info("PostsList()");
		log.info("roomId " + roomId);
		log.info("user " + userInfo.getUserId());
		log.info("session Id : "+sessionId);
		return "forward:/main";
	}
}
