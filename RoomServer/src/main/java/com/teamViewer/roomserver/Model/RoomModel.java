package com.teamViewer.roomserver.Model;


import javax.persistence.*;
/*lombok을 통한 getter, setter*/
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*issue#9 IntelliJ Lombok 플러그인 설치후 get, set 함수 제거*/
@Entity
@Table(name = "rooms")
@Getter
@Setter
public class RoomModel {
    /*@Id를 이용하여 기본 키 설정*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto IncId Generator
    private int roomNum;
    /*@Column을 이용하여 데이터 베이스 컬럼 설정*/
    @Column
    private String roomId;
    @Column
    private String userId;
    @Column
    private String name;

    @Builder
    public RoomModel(String _roomId, String _userId, String _name) {
        this.roomId = _roomId;
        this.userId = _userId;
        this.name = _name;
    }

    public RoomModel() {

    }

}


