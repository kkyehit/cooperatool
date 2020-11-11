var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        });
        ///topic/${roomID}
        stompClient.subscribe('/topic/'+$("#roomID").val(), function (chat) {
            showChat(JSON.parse(chat.body));
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
}

function sendChat() {
    var roomId=$("#roomID").val();
    //"/app/${roomId}"
    //"/app/"+$("#roomID").val()+"/"
    stompClient.send("/app/"+$("#roomID").val(), {}, JSON.stringify({'roomId':$("#roomID").val(),'userId': $("#name").val(), 'chatStr': $("#chatMessage").val()}));
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}
function showChat(chat) {
    $("#greetings").append("<tr><td>" + chat.userId + " : " + chat.chatStr + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });

    $( "#chatSend" ).click(function(){ sendChat(); });
});