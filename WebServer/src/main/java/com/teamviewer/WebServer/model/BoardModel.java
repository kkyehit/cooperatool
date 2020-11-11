package com.teamviewer.WebServer.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardModel {
	private int boardId;
	private String title;
	private String contents;
	private String userId;
	private int roomNum;
}
