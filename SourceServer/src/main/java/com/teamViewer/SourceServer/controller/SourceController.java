package com.teamViewer.SourceServer.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.teamViewer.SourceServer.model.MessageModel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SourceController {
	@MessageMapping("/{roomId}")
	@SendTo("/source/{roomId}")
	public MessageModel msg(@RequestBody MessageModel messageModel){
		log.info(messageModel.getCol()+" "+messageModel.getRow()+" "+messageModel.getCharacter()+" "+messageModel.getCommand());
		return messageModel;
	}
}
