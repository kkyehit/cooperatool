package com.teamviewer.paintserver.controller;

import java.lang.reflect.Method;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.teamviewer.paintserver.model.PaintModel;
import com.teamviewer.paintserver.model.request.PaintRequestModel;
import com.teamviewer.paintserver.service.PaintService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class PaintController {

	@Autowired
	PaintService paintService;

	@MessageMapping("/{roomId}")
	@SendTo("/canvas/{roomId}")
	public PaintModel doPaint(@RequestBody PaintRequestModel paintRequestModel){
		log.info("called doPaint() --- " + paintRequestModel.getRoomId());
		log.info(paintRequestModel.toString());
		paintService.doPaint(paintRequestModel.getRoomId(), paintRequestModel.getPaintModel());
		return paintRequestModel.getPaintModel();
	}
	@RequestMapping(path = "/paint/{roomId}", method = RequestMethod.GET)
	public List<PaintModel> getPaint(@PathVariable("roomId") String roomId){
		log.info("called getPaint() --- " + roomId);
		return paintService.getPaint(roomId);
	}
}
