package com.teamViewer.userServer.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestModel {
	String userId;
	String userPw;
}
