package com.teamViewer.boardServer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teamViewer.boardServer.model.BoardModel;

@Repository
public interface BoardRepository extends JpaRepository<BoardModel, Integer> {
	public List<BoardModel> findListByRoomNum(Integer roomNum);
	public Optional<BoardModel> findContentByBoardId(Integer boardId);
}
