var  webserver = "http://20.196.136.212:5555"+"/webserver"

function refreshRoomList(userId, sessionId){
    $.ajax({
        type:"GET",
        url : webserver+"/RoomList/"+userId+"?sessionId="+sessionId,
        accept : "application/json",
        contentType : "application/json",
        dataType : 'text',
        success: successRoomList(""),
    })
    function successRoomList(result){
        location.reload();
    }
    console.log("user " + userId);
}

function refreshBoardList(roomId, sessionId){
    console.log("activated " + roomId);
    $.ajax({
        type:"GET",
        url : webserver+'/PostsList/'+roomId+"?sessionId="+sessionId,
        accept : "application/json",
        contentType : "application/json",
        dataType : 'text',
        success: successBoardList(""),
    })
    function successBoardList(result){
        location.reload();
    }
}
