function createPosts( title,  contents,  userId,  roomId){
    var obj = new Object();
    obj.userId = userId;
    obj.title = title;
    obj.contents = contents;
    obj.roomNum = roomId;
     //JSON 으로 ID 랑 Roomname 넘겨줘야함
     $.ajax({
        type:"POST",
        url : zuulserver+"/boardserver/boards/",
        accept : "application/json",
        contentType : "application/json",
        dataType : 'text',
        data: JSON.stringify(obj),
        //success: successRoomCreate(result),
        success: successBoardCreate(""),
        error : errorBoardCreate
        })
        function successBoardCreate(result){
            alert("게시글 작성을 성공하였습니다.");
            refreshBoardList(roomId);
        }
        function errorBoardCreate(){
            alert("게시글 작성을 실패하였습니다.");
            refreshBoardList(roomId);
        }
}

function selectPost(title, postContents, userId){
    var myDiv = document.getElementById("new_contents");
    if(myDiv != null){
        var parent = myDiv.parentElement; // 부모 객체 알아내기
        parent.removeChild(myDiv); // 부모로부터 myDiv 객체 떼어내기
    }
    var newDIV = document.createElement("div");
    var board_test = document.getElementById("board");
    var contents = document.getElementById("writeBoard");
    newDIV.setAttribute("class","input-group col-xs-12");
    newDIV.setAttribute("id", "new_contents");
    newDIV.innerHTML = [
        "제목 : ",
        title,
        "<br>",
        "작성자 : ",
        userId,
        "<br>",
        "내용 : <br>",
        postContents,
        "<br>",
        "<button type='button' id='close_text' class='btn pull-right btn-danger'>닫기</button>"
    ].join(" ");

    board_test.insertBefore(newDIV,contents);
}