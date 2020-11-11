var localVideo;
var firstPerson = false;
var socketCount = 0;
var socketId;
var localStream;
var connections = [];
var ids = [];
var idCount = 0;

var peerConnectionConfig = {
    'iceServers': [
        {'urls': 'stun:stun.services.mozilla.com'},
        {'urls': 'stun:stun.l.google.com:19302'},
    ]
};
function closeVideo(){
    localVideo.pause();
    socket.close();
    connections = [];
    for(var id = 0; id < idCount; id++){
        var video = document.querySelector('[data-socket="'+ ids[id] +'"]');
        var parentDiv = video.parentElement;
        video.pause();
        video.parentElement.parentElement.removeChild(parentDiv);
    }

    idCount = 0;
}

function init(){
    if (navigator.mediaDevices === undefined) {
      navigator.mediaDevices = {};
    }else return;

    // Some browsers partially implement mediaDevices. We can't just assign an object
    // with getUserMedia as it would overwrite existing properties.
    // Here, we will just add the getUserMedia property if it's missing.
    if (navigator.mediaDevices.getUserMedia === undefined) {
      navigator.mediaDevices.getUserMedia = function(constraints) {

        // First get ahold of the legacy getUserMedia, if present
        var getUserMedia = navigator.webkitGetUserMedia || navigator.mozGetUserMedia;

        // Some browsers just don't implement it - return a rejected promise with an error
        // to keep a consistent interface
        if (!getUserMedia) {
          return Promise.reject(new Error('getUserMedia is not implemented in this browser'));
        }

        // Otherwise, wrap the call to the old navigator.getUserMedia with a Promise
        return new Promise(function(resolve, reject) {
          getUserMedia.call(navigator, constraints, resolve, reject);
        });
      }
    }
}

function pageReady() {
    init();

    localVideo = document.getElementById('localVideo');
    remoteVideo = document.getElementById('remoteVideo');

    var constraints = {
        video: true,
        audio: true,
    };

    if(navigator.mediaDevices.getUserMedia) {
        navigator.mediaDevices.getUserMedia(constraints)
            .then(getUserMediaSuccess)
            .then(function(){

                socket = io.connect(config.host, {secure: true});
                socket.on('signal', gotMessageFromServer);    

                socket.on('connect', function(){

                    socketId = socket.id;

                    socket.on('user-left', function(id){
                        var video = document.querySelector('[data-socket="'+ id +'"]');
                        var parentDiv = video.parentElement;
                        video.parentElement.parentElement.removeChild(parentDiv);
                    });


                    socket.on('user-joined', function(id, count, clients){
                        clients.forEach(function(socketListId) {
                            if(!connections[socketListId]){
                                connections[socketListId] = new RTCPeerConnection(peerConnectionConfig);
                                //Wait for their ice candidate       
                                connections[socketListId].onicecandidate = function(){
                                    if(event.candidate != null) {
                                        console.log('SENDING ICE');
                                        socket.emit('signal', socketListId, JSON.stringify({'ice': event.candidate}));
                                    }
                                }

                                //Wait for their video stream
                                connections[socketListId].onaddstream = function(){
                                    gotRemoteStream(event, socketListId)
                                }    

                                //Add the local video stream
                                connections[socketListId].addStream(localStream);                                                                
                            }
                        });

                        //Create an offer to connect with your local description
                        
                        if(count >= 2){
                            connections[id].createOffer().then(function(description){
                                connections[id].setLocalDescription(description).then(function() {
                                    // console.log(connections);
                                    socket.emit('signal', id, JSON.stringify({'sdp': connections[id].localDescription}));
                                }).catch(e => console.log(e));        
                            });
                        }
                    });                    
                })       
        
            }); 
    } else {
        alert('Your browser does not support getUserMedia API');
    }
}

function getUserMediaSuccess(stream) {
    localStream = stream;
   /**This error is caused because the function createObjectURL is deprecated for Google Chrome**/
   //localVideo.src = window.URL.createObjectURL(stream);
   localVideo.srcObject=stream;
   localVideo.play();
}

function gotRemoteStream(event, id) {

    var videos = document.querySelectorAll('video'),
        video  = document.createElement('video'),
        div    = document.createElement('div')

    video.setAttribute('data-socket', id);
    video.setAttribute('width', 320);
    video.setAttribute('height', 240);
    /**This error is caused because the function createObjectURL is deprecated for Google Chrome**/
    //video.src         = window.URL.createObjectURL(event.stream);
    video.srcObject=event.stream;
    video.play();
    
    video.autoplay    = true; 
    //video.muted       = true;
    video.playsinline = true;
    
    div.appendChild(video);      
    document.querySelector('.videos').appendChild(div);

    ids[idCount++] = id;
}

function gotMessageFromServer(fromId, message) {

    //Parse the incoming signal
    var signal = JSON.parse(message)

    //Make sure it's not coming from yourself
    if(fromId != socketId) {

        if(signal.sdp){            
            connections[fromId].setRemoteDescription(new RTCSessionDescription(signal.sdp)).then(function() {                
                if(signal.sdp.type == 'offer') {
                    connections[fromId].createAnswer().then(function(description){
                        connections[fromId].setLocalDescription(description).then(function() {
                            socket.emit('signal', fromId, JSON.stringify({'sdp': connections[fromId].localDescription}));
                        }).catch(e => console.log(e));        
                    }).catch(e => console.log(e));
                }
            }).catch(e => console.log(e));
        }
    
        if(signal.ice) {
            connections[fromId].addIceCandidate(new RTCIceCandidate(signal.ice)).catch(e => console.log(e));
        }                
    }
}