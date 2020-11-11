var canvas, context;
var stompClientForCanvas = null;
var paintServer = "http://20.196.136.212:5556/paintserver"
var room;
function connectForCanvas(roomId) {
    var socket = new SockJS(paintServer + '/websocket');
    room = roomId;
    console.log("roomId : "+roomId+", room : "+room);
    stompClientForCanvas = Stomp.over(socket);
    stompClientForCanvas.connect({}, function (frame) {
        setConnected(true);
        console.log('Paint Connected: ' + frame);
        ///topic/${roomID}
        stompClientForCanvas.subscribe('/canvas/' + roomId, function (point) { //받는거
            console.log(" points : "+point.body);
            drawByPoint(JSON.parse(point.body));
        });
        $.get(paintServer+'/paint/' + roomId, function (data) { drawByList(data) })

    });
}

function disconnectForCanvas() {
    if (stompClient !== null) {
        stompClientForCanvas.disconnect();
    }
    console.log("DisconnectedForCanvas");
}


function sendToCanvas(roomId, x, y, color, width) {
    console.log("send to -> "+roomId);
    stompClientForCanvas.send("/app/" + roomId, {}, JSON.stringify({ 'roomId': roomId, 'x': x, 'y': y, 'color':color, 'width':width }));
}

window.onload = function () {
  canvas = document.getElementById("cnvs");
  range = document.getElementById("jsRange");
  Erange = document.getElementById("jsERange");
  context = canvas.getContext("2d");
  ccolor = document.getElementById("base");
  initial = document.getElementById("initialization");

  penRadio = document.getElementById("penRadio");
  eraserRadio = document.getElementById("eraserRadio");

  context.lineWidth = 5; //초기 펜 굵기 5로 설정
  context.strokeStyle = ccolor.value;

  // 마우스 리스너 등록. e는 MouseEvent 객체

  canvas.addEventListener(
    "mousemove",
    function (e) {
      move(e);
    },
    false
  );

  canvas.addEventListener(
    "mousedown",
    function (e) {
      down(e);
    },
    false
  );

  canvas.addEventListener(
    "mouseup",
    function (e) {
      up(e);
    },
    false
  );

  canvas.addEventListener(
    "mouseout",
    function (e) {
      out(e);
    },
    false
  );

  range.addEventListener(
    "input",
    function (e) {
      penRadio.checked = true;
      rangechange(e);
    },
    false
  );

  Erange.addEventListener(
    "input",
    function (e) {
      eraserRadio.checked = true;
      Erangechange(e);
    },
    false
  );

  ccolor.addEventListener(
    "input",
    function (e) {
      colorchange(e);
    },
    false
  );

  initial.addEventListener(
    "click",
    function (e) {
      initialize(e);
    },
    false
  );

  penRadio.addEventListener("click", function (e) {
    brushWidth = range.value;
    context.lineWidth = brushWidth;
    context.strokeStyle = ccolor.value;
  });

  eraserRadio.addEventListener("click", function (e) {
    EbrushWidth = Erange.value;
    context.lineWidth = EbrushWidth;
    context.strokeStyle = "#FFFFFF";
  });

};

let testObj = [
  {
    x: 1,
    y: 1,
    w: 5,
    c: "#ff0000",
  },
  {
    x: 2,
    y: 2,
    w: 5,
    c: "#000000",
  },
  {
    x: 50,
    y: 50,
    w: 5,
    c: "#ff0000",
  },
  {
    x: 340,
    y: 149,
    w: 10,
    c: "#ff0099",
  },
];
function drawByList(data) {
  for (idx in data) {
    context.lineWidth = data[idx].width;
    context.strokeStyle = data[idx].color;
    startX = data[idx].x;
    startY = data[idx].y;
    draw(data[idx].x*1 + context.lineWidth, data[idx].y*1 + context.lineWidth); //숫자형으로 변환하기 위해 *1
  }
  context.lineWidth = document.getElementById("jsRange").value;
  context.strokeStyle = document.getElementById("base").value;
}
function drawByPoint(data) {
  context.lineWidth = data.width;
  context.strokeStyle = data.color;
  startX = data.x;
  startY = data.y;
  draw(data.x*1 + context.lineWidth, data.y*1 + context.lineWidth); //숫자형으로 변환하기 위해 *1
  context.lineWidth = document.getElementById("jsRange").value;
  context.strokeStyle = document.getElementById("base").value;
}

var startX = 0,
  startY = 0; // 드래깅동안, 처음 마우스가 눌러진 좌표

var drawing = false;

function draw(curX, curY) {
  context.beginPath();

  context.moveTo(startX, startY);

  context.lineTo(curX, curY);

  context.stroke();
  console.log("draw : ["+startX+", "+startY+"] to ["+curX+", "+curY+"] width : "+context.lineWidth+" color : "+context.strokeStyle);
}

function down(e) {
  // @@@@@@서버로 좌표 보내야 하는 곳 후보 1@@@@@@@@
  startX = e.offsetX;
  startY = e.offsetY;
  draw(startX + context.lineWidth, startY + context.lineWidth);
  sendToCanvas(room, startX, startY, context.strokeStyle, context.lineWidth);

  //console.log(`x : ${startX} y: ${startY}`);
  drawing = true;
}

function up(e) {
  drawing = false;
}

function move(e) {
  // @@@@@ 서버로 좌표 보내야허는 곳 후보2@@@@@@@@@
  if (!drawing) return; // 마우스가 눌러지지 않았으면 리턴

  var curX = e.offsetX,
    curY = e.offsetY;

  draw(curX, curY);
  sendToCanvas(room, curX, curY,  context.strokeStyle, context.lineWidth);
  // console.log(
  //   `curX : ${curX}, curY : ${curY}, color : ${context.strokeStyle}, width: ${context.lineWidth}`
  // );
  startX = curX;
  startY = curY;
}

function out(e) {
  drawing = false;
}

function rangechange(e) {
  brushWidth = e.target.value;
  context.lineWidth = brushWidth;
  context.strokeStyle = ccolor.value;
}

function Erangechange(e) {
  EbrushWidth = e.target.value;
  context.lineWidth = EbrushWidth;
  context.strokeStyle = "#FFFFFF";
}

function colorchange(e) {
  brushcolor = e.target.value;
  context.strokeStyle = brushcolor;
  context.lineWidth = brushWidth;
}

function initialize(e) {
  context.clearRect(0, 0, canvas.width, canvas.height);
}
