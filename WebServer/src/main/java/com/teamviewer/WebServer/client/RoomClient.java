package com.teamviewer.WebServer.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.teamviewer.WebServer.model.RoomModel;

@FeignClient("roomserver")
public interface RoomClient {
	@RequestMapping(value = "/rooms/{userId}", method = RequestMethod.GET)
	public List<RoomModel> roomListById(@PathVariable("userId") String userId) throws Exception;
}

