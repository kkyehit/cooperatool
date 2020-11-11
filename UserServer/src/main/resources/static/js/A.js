<script src="js/jquery-3.4.1.min.js"></script>

    function login(sel){
        $.ajax({

            url : $(sel).attr('action'), //ajax url
            dataType : "json", // ajax 통신의 데아터 형식
            async : false, // 동기false, 비동기ture
            type : $(sel).attr('method'),
            data : $(sel).seialize(),
            success : function(data){
                
                if(data['result'] == 'f'){ // 로그인 실패
                    alert(data['msg']);
                }
                else if(data['result'] == 'success'){ // 로그인 성공시
                    alert(data['msg']);
                    $('#login').html(data['id']+ '님 환영합니다.');
                }
            },
            error: function(xhr, status, error){

                var error_confirm = confirm('데이터 전송 오류 입니다. 확인을 누르시면 페이지가 새로고침 됩니다.');
                if (error_confirm == true){
                    document.location.reload();
                }
            }
        });
        return true;
    }