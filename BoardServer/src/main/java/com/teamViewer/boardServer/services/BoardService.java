package com.teamViewer.boardServer.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamViewer.boardServer.exception.NoDataException;
import com.teamViewer.boardServer.model.BoardModel;
import com.teamViewer.boardServer.repository.BoardRepository;

@Service
public class BoardService {
	@Autowired
	BoardRepository boardRepository;

	public void save(BoardModel boardModel){
		boardRepository.save(boardModel);
	}

	public void delete(Integer boardId) throws NoDataException {
		boardRepository.delete(this.findByBoardId(boardId));
	}

	public List<BoardModel> findListByRoomId(Integer roomNum){
		return boardRepository.findListByRoomNum(roomNum);
	}
	public BoardModel findByBoardId(Integer boardId) throws NoDataException{
		return boardRepository.findContentByBoardId(boardId).orElseThrow(NoDataException::new);
	}
}
