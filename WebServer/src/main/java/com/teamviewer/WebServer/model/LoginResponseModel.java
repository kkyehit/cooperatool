package com.teamviewer.WebServer.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseModel {
	String userId;
	String name;

	public LoginResponseModel(String _userId, String _name) {
		userId = _userId; name = _name;
	}
}
