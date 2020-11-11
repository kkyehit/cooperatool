package com.teamViewer.boardServer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "boards")
@Getter
@Setter
public class BoardModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto IncId Generator
	private int boardId;
	@Column
	private String title;
	@Column
	private String contents;
	@Column
	private String userId;
	@Column
	private int roomNum;

}
