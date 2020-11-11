package com.teamviewer.WebServer.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.teamviewer.WebServer.model.BoardModel;

@FeignClient("boardserver")
public interface BoardClient {
	@RequestMapping(path = "/boards/room/{roomNum}", method = RequestMethod.GET)
	public List<BoardModel> getBoardList(@PathVariable("roomNum") Integer roomNum);

	@RequestMapping(path = "/boards/board/{boardId}", method = RequestMethod.GET)
	public BoardModel getContent(@PathVariable("boardId") Integer boardId);
}
