package com.teamviewer.WebServer.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestModel {
	String userId;
	String userPw;

	public LoginRequestModel(String _userId, String _userPw) {
		userId = _userId; userPw = _userPw;
	}
}
