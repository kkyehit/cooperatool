package com.teamviewer.WebServer.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.teamviewer.WebServer.model.LoginRequestModel;
import com.teamviewer.WebServer.model.LoginResponseModel;

@FeignClient("userserver")
public interface LoginClient {
	@RequestMapping(value = "/users/login", method = RequestMethod.POST)
	public LoginResponseModel login(@RequestBody LoginRequestModel loginRequestModel) throws Exception;
}
