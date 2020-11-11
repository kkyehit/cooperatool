var sourceClient;
var loginId;
function sourceConnect(roomId, userId) {
    var socket = new SockJS('http://20.196.136.212:5557/sourceserver/websocket');
    sourceClient = Stomp.over(socket);
    sourceClient.connect({}, function (frame) {
        setConnected(true);
        console.log('source Chatting Connected: ' + frame);

        loginId = userId;
        ///topic/${roomID}
        sourceClient.subscribe('/source/' + roomId, function (chat) { //받는거
            showCode(JSON.parse(chat.body), loginId);
        });
    });
}

function sourceDisconnect() {
    if (sourceClient !== null) {
        sourceClient.disconnect();
    }
    console.log("source Disconnected");
}


function sendCode(args, command, column, row, column2, row2, roomId, userId) {
    //"/app/${roomId}"
    //"/app/"+$("#roomID").val()+"/"
    //stompClient.send("/app/" + roomId, {}, JSON.stringify({ 'roomId': roomId, 'userId': userId, 'chatStr': $("#btn-input").val() }));
    sourceClient.send("/source/" + roomId, {}, JSON.stringify({ 'col': column, 'row': row, 'col2': column2, 'row2': row2, 'character': args, 'command' : command, 'userId' : userId}));

}

function showCode(msg, userId) {
    console.log(msg);
    console.log("compare user Id "+userId);
    console.log(ace.edit("editor").selection.getRange());
    if(msg.userId === userId){
        return;
    }

    if(msg.command === 'insertstring'){
        ace.edit("editor").session.insert({row:msg.row, column:msg.col}, msg.character);
    }else{
        ace.edit("editor").session.replace({end: {row: msg.row, column: msg.col}, start: {row: msg.row2, column: msg.col2}}, "");
    }
    inputCheck = 1;

    //ace.edit("editor").moveCursorTo(msg.row, msg.column);
    //ace.edit("editor").session.
}