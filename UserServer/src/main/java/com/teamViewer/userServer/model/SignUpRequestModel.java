package com.teamViewer.userServer.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequestModel {
	String name;
	String userId;
	String userPw;
}
