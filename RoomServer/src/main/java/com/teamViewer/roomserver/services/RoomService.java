package com.teamViewer.roomserver.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import com.teamViewer.roomserver.Exception.NoDataException;
import com.teamViewer.roomserver.Model.Request.CreateRoomRequest;
import com.teamViewer.roomserver.Model.Request.RoomListRequest;
import com.teamViewer.roomserver.Model.Request.UserEnterRequest;
import com.teamViewer.roomserver.Model.Request.UserExitRequest;
import com.teamViewer.roomserver.Model.RoomModel;
import com.teamViewer.roomserver.repository.RoomRepository;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/*Service의 역할은 Dao가 DB에서 받아온 데이터를 전달받아 가공하는 것*/
@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    //되는거 확인
    public void enterUser(UserEnterRequest joinRequestModel) throws Exception{
        List<RoomModel> list= roomRepository.findAllByRoomId(joinRequestModel.roomId);
        if(list.size() == 0) throw new Exception();
        else joinRequestModel.setName(list.get(0).getName());
        RoomModel roomModel = new RoomModel(joinRequestModel.getRoomId(), joinRequestModel.getUserId(), joinRequestModel.getName());
        roomRepository.save(roomModel);
    }
    //되는거 확인
    public void exitUser(UserExitRequest exitRequestModel) throws NoDataException{
        roomRepository.delete(roomRepository.findByRoomIdAndUserId(exitRequestModel.getRoomId(), exitRequestModel.getUserId()).orElseThrow(NoDataException::new));
    }

    //이것들 해야돼
    public List getRoomList(String userId){
        return roomRepository.findAllByUserId(userId);
    }

    public List getUserList(String roomId){
        return roomRepository.findAllByRoomId(roomId);
    }


    //되는거 확인
    public String createRoom(String userId, String name) {
        //roomId생성하고(중복허용 X) roomRequestModel의 userId와 roomName을 더해서 테이블에 튜플 추가하고 id 리턴

        LocalDateTime now = LocalDateTime.now();
        int roomId = (int)( ( (long) now.hashCode() + 2123456789) % 2123456789);

        //난수생성
        String roomIdString = Integer.toString(roomId);

        while(roomRepository.findByRoomId(roomIdString).isPresent()) {
            roomId = (int)( ( (long) Math.random() + 2123456789) % 2123456789);
            roomIdString = Integer.toString(roomId);
        }

        RoomModel newRoom = new RoomModel(roomIdString, userId, name);
        roomRepository.save(newRoom);
        return roomIdString;
    }

}
