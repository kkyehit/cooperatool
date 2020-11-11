package com.teamViewer.roomserver.repository;

import java.util.List;
import java.util.Optional;

import com.teamViewer.roomserver.Model.RoomModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/*JPA 에서는 단순히 Repository 인터페이스를 생성한후
JpaRepository<Entity, 기본키 타입> 을 extends하면 기본적인 Create, Read, Update, Delete가 자동으로 생성된다.*/
@Repository
public interface RoomRepository extends JpaRepository<RoomModel, String> {
    public Optional<RoomModel> findByRoomIdAndUserId(String roomId, String userId);
    public List<RoomModel> findAllByUserId(String userId);
    public List<RoomModel> findAllByRoomId(String userId);
    public Optional<RoomModel> findByRoomId(String roomId);
}
