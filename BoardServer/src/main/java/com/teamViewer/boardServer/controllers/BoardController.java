package com.teamViewer.boardServer.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.teamViewer.boardServer.exception.NoDataException;
import com.teamViewer.boardServer.model.BoardModel;
import com.teamViewer.boardServer.model.request.BoardDeleteRequestModel;
import com.teamViewer.boardServer.services.BoardService;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/boards")
@RestController
@Slf4j
public class BoardController {
	@Autowired
	BoardService boardService;

	@RequestMapping(path = "/", method = RequestMethod.POST)
	public void save(@RequestBody BoardModel boardModel){
		boardService.save(boardModel);
	}

	@RequestMapping(path = "/", method = RequestMethod.DELETE)
	public void delete(@RequestBody BoardDeleteRequestModel boardDeleteRequestModel) throws NoDataException {
		boardService.delete(boardDeleteRequestModel.getBoardId());
	}

	@RequestMapping(path = "/room/{roomNum}", method = RequestMethod.GET)
	public List<BoardModel> getBoardList(@PathVariable("roomNum") Integer roomNum){
		return boardService.findListByRoomId(roomNum);
	}


	@RequestMapping(path = "/board/{boardId}", method = RequestMethod.GET)
	public BoardModel getContent(@PathVariable("boardId") Integer boardId) throws NoDataException {
		return boardService.findByBoardId(boardId);
	}

	/*Exception Handler*/
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = {NoDataException.class})
	public String userErrorHandler(Exception e){
		/*issue#6  HTTP Status Code 활용*/
		log.debug("error");
		return e.toString();
	}
}
