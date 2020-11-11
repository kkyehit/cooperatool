package com.teamviewer.WebServer.session;

import java.io.Serializable;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.context.WebApplicationContext;

import com.teamviewer.WebServer.model.BoardModel;
import com.teamviewer.WebServer.model.RoomModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Component
@Getter
@Setter
@ToString
public class UserInfo {
	private static final long serialVersionUID = 1L;
	private Integer activatedRoom;
	private String userId;
	private String name;
	private List<RoomModel> roomModelList;
	private List<BoardModel> boardList;
}
