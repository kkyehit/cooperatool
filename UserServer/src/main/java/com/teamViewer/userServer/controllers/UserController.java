package com.teamViewer.userServer.controllers;

import java.util.IllegalFormatException;
import java.util.Optional;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.teamViewer.userServer.exception.DuplicateDataError;
import com.teamViewer.userServer.exception.NoDataException;
import com.teamViewer.userServer.model.LoginRequestModel;
import com.teamViewer.userServer.model.LoginResponseModel;
import com.teamViewer.userServer.model.SignUpRequestModel;
import com.teamViewer.userServer.model.UserModel;
import com.teamViewer.userServer.services.UserService;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin("**")
@RequestMapping("/users")
@RestController
@ControllerAdvice
@Slf4j
public class UserController {

	@Autowired
	UserService userService;

	UserModel users;

	/*issue#7 추측가능한 url 제거*/
	@ResponseBody
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String signUp(@RequestBody SignUpRequestModel signUpRequestModel) throws DuplicateDataError {
		log.info("signup " + signUpRequestModel.getUserId()+ " / "+ signUpRequestModel.getUserPw());
		userService.signUp(signUpRequestModel);
		return "ok";
	}

	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public LoginResponseModel login(@RequestBody LoginRequestModel loginRequestModel) throws NoDataException {
		log.info("login " + loginRequestModel.getUserId());
		users =  userService.login(loginRequestModel);
		LoginResponseModel res = new LoginResponseModel(users.getUserId() , users.getName());
		return res;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public UserModel findById(@PathVariable("id") String id) throws NoDataException {
		log.debug("get ID");
		return userService.findByUserId(id);
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(){
		/*issue#4 sysout 대신 log4j사용*/
		/*@slf4j로 log*/
		log.debug("test");
		return "test result";
	}

	/*Exception Handler*/
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = {NoDataException.class, DuplicateDataError.class})
	public String userErrorHandler(Exception e){
		/*issue#6  HTTP Status Code 활용*/
		log.debug("error");
		return e.toString();
	}
}
