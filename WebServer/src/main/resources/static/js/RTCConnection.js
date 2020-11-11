
   var conn = new WebSocket('ws://192.168.43.70:8200/socket');

   conn.onopen = function() {
       console.log("Connected to the signaling server");
       initialize();
   };

   conn.onmessage = function(msg) {
       console.log("Got message", msg.data);
       var content = JSON.parse(msg.data);
       var data = content.data;
       switch (content.event) {
       // when somebody wants to call us
       case "offer":
           handleOffer(data);
           break;
       case "answer":
           handleAnswer(data);
           break;
       // when a remote peer sends an ice candidate to us
       case "candidate":
           handleCandidate(data);
           break;
       default:
           break;
       }
   };

   function send(message) {
       conn.send(JSON.stringify(message));
   }

   var peerConnection;
   var dataChannel;
   var input = document.getElementById("messageInput");
   var videoElement

   function initialize() {
       var configuration = null;

       peerConnection = new RTCPeerConnection(configuration, {
           optional : [ {
               RtpDataChannels : true
           } ]
       });

       // Setup ice handling
       peerConnection.onicecandidate = function(event) {
           if (event.candidate) {
               send({
                   event : "candidate",
                   data : event.candidate
               });
           }
       };

        peerConnection.onaddstream = function(event) {
            document.querySelector('video').srcObject = event.stream;
            //videoElement=Document.createElement('video');
            //videoElement.srcObject = event.stream;
        };

       // creating data channel
       dataChannel = peerConnection.createDataChannel("dataChannel", {
           reliable : true
       });

       dataChannel.onerror = function(error) {
           console.log("Error occured on datachannel:", error);
       };

       // when we receive a message from the other peer, printing it on the console
       dataChannel.onmessage = function(event) {
           console.log("message:", event.data);
       };

       dataChannel.onclose = function() {
           console.log("data channel is closed");
       };
   }

   function createOffer() {
       peerConnection.createOffer(function(offer) {
           send({
               event : "offer",
               data : offer
           });
           peerConnection.setLocalDescription(offer);
       }, function(error) {
           alert("Error creating an offer");
       });
   }

   function handleOffer(offer) {
       peerConnection.setRemoteDescription(new RTCSessionDescription(offer));

       // create and send an answer to an offer
       peerConnection.createAnswer(function(answer) {
           peerConnection.setLocalDescription(answer);
           send({
               event : "answer",
               data : answer
           });
       }, function(error) {
           alert("Error creating an answer");
       });

   };

   function handleCandidate(candidate) {
       peerConnection.addIceCandidate(new RTCIceCandidate(candidate));
   };

   function handleAnswer(answer) {
       peerConnection.setRemoteDescription(new RTCSessionDescription(answer));
       console.log("connection established successfully!!");
   };

   function sendMessage() {
       dataChannel.send(input.value);
       input.value = "";
   }

   /**
     * getUserMedia 성공
     * @param stream
   */
   function success(stream) {
      console.log('success', arguments);

      // 비디오 테그에 stream 바인딩
      document.querySelector('video').srcObject = stream;

       // do something...
    }

    /**
    * getUserMedia 실패
    * @param error
    */
    function error(error) {
       console.log('error', arguments);

       alert('카메라와 마이크를 허용해주세요');
    }
   /*마이크와 카메라 허용*/

   function onUserMedia() {
      alert('카메라와 마이크 허용');
      navigator.mediaDevices.getUserMedia({ audio: true, video: true }, success, error)
        .then(function(stream){
            peerConnection.addStream(stream);
        }).catch(function(err){
      });
   };



