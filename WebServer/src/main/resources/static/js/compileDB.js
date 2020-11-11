var dbserver= "http://20.196.136.212:5555/dbserver"
function compileDB(language, roomId, contents, isSelect) {
  var obj = new Object();
  var result;

  obj.language = language;
  obj.query = contents;
  obj.roomId = roomId;
  obj.isSelect = isSelect;

  return getResultDB(obj);
}

function getResultDB(obj) {
  var result;
  if (obj.isSelect) {
    $.ajax({
      type: "POST",
      url: dbserver+"/db/select/", // /db/select
      accept: "application/json",
      contentType: "application/json",
      dataType: "json",
      async: false,
      data: JSON.stringify(obj),
      //success: successRoomCreate(result),
      success: function (response) {
        result = response;
      },
    });
  } else {
    $.ajax({
      type: "POST",
      url: dbserver+"/db/others/", // /db/others
      accept: "application/json",
      contentType: "application/json",
      dataType: "text",
      async: false,
      data: JSON.stringify(obj),
      //success: successRoomCreate(result),
      success: function (response) {
        result = response;
      },
    });
  }

  return result;
}
