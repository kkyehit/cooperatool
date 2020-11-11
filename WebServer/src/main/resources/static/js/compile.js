function compile(language, roomId, contents, mainClass){
    var obj = new Object();
    var result;
    obj.language = language;
    obj.contents = contents;
    obj.roomId = roomId;
    obj.mainClass = mainClass;

    return getResult(obj);
}

function getResult(obj){
   var result;
    $.ajax({
        type:"POST",
        url : zuulserver+"/executeserver/execute/"+obj.language,
        accept : "application/json",
        contentType : "application/json",
        dataType : 'text',
        async:false,
        data: JSON.stringify(obj),
        //success: successRoomCreate(result),
        success:function(response){
            result = response;
        }
    });
    return result;
}