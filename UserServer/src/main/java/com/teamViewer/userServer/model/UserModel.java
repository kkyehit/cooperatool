package com.teamViewer.userServer.model;

import javax.persistence.*;
/*lombok을 통한 getter, setter*/
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*issue#9 IntelliJ Lombok 플러그인 설치후 get, set 함수 제거*/
@Entity
@Table(name = "users")
@Getter
@Setter
public class UserModel {
	/*@Id를 이용하여 기본 키 설정*/
	@Id
	//@GeneratedValue(strategy= GenerationType.IDENTITY) // Auto IncId Generator
	//private int num;
	/*@Column을 이용하여 데이터베이스 컬럼 설정*/
	@Column
	private String userId;
	@Column
	private String userPw;
	@Column
	private String name;

	@Builder
	public UserModel(String _userId, String _userPw, String _name){
		this.userId = _userId;
		this.userPw = _userPw;
		this.name = _name;
	}

	public UserModel() {

	}

	public String getName() {
		return name;
	}
}
