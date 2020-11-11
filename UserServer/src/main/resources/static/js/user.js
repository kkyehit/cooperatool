src="js/jquery-3.4.1.min.js"

function fnLogin(){
    var objID = document.login.loginid.value;
    var objPW = document.login.loginpw.value;
    // var objLogin = document.getElementById("loginid");
    // var objPassword = document.getElementById("loginpw");

    if(objID === ""){
        alert("아이디를 입력하세요");
        objID.focus();
        return;
    }
    if(objPW === ""){
        alert("비밀번호를 입력하세요");
        objPW.focus();
        return;
    }

    var loginJson = new Object();
    loginJson.id = objID;
    loginJson.pw = objPW;
    var checkJson = JSON.stringify(loginJson);
    console.log(checkJson); //JSON 형식 테스트 출력
    
    
    // var xhr = new XMLHttpRequest();
    // const url = "http://www.naver.com";
    // xhr.open("POST",url,true);
    // //xhr.setRequestHeader('Ping-Other', 'pingpong');
    // xhr.setRequestHeader("Content-Type", "application/json");

    // xhr.send(JSON.stringify(loginJson));

    // xhr.onreadystatechange = function()
    // {
    //     if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200){
    //         console.log(xhr.responseText);
    //         // response 받은 text를 콘솔에 출력
    //     }
    // }

    $.ajax({
        type : "POST",
        url : "http://localhost:8080/users",
        dataType : 'json',
        contentType : 'application/json',
        data : JSON.stringify(loginJson),
        success : successCall,
        error : errorCall
    });

    function successCall(){
        alert("전송성공");
    }

    function errorCall(){
        alert("전송실패");
    }
}

function signUp(){
    var signNAME = document.sign.signname.value;
    var signID = document.sign.signid.value;
    var signPW = document.sign.signpw.value;

    if(signNAME === ""){
        alert("이름을 입력하세요");
        signNAME.focus();
        return;
    }
    if(signID === ""){
        alert("아이디을 입력하세요");
        signID.focus();
        return;
    }
    if(signPW === ""){
        alert("비밀번호을 입력하세요");
        signPW.focus();
        return;
    }

    
    
    var signObj = new Object();
    signObj.name = signNAME;
    signObj.id = signID;
    signObj.pw = signPW;

    var signJSON = JSON.stringify(signObj);
    console.log(signJSON); //JSON 형식 테스트 출력

    // TODO : DB에 JSON 형식으로 전송하기


}