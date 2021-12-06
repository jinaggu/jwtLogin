$(document).ready(function() {
    var token = "";
    var tokenCheck = false;
    $("#login").click(function() {
       var email = $("#email").val();
       var pw = $("#pw").val();
        debugger;
       var jwtToken = createToken(email);
       var isToken = tokenVerification(email,jwtToken);

        if (isToken) {
            location.href = '/sample/success3?email='+email;
        } else {
            return false;
        }
    });

});

    // 로그인시 토큰생성 함수
    function createToken(email) {
        $.ajax({
            url : '/process/jwtLogin?email='+email,
            contentType : 'application/json; charset=utf-8',
            type : "post",
            async : false,
            success : function(result) {
                console.log("result : " + result);
                token = result;
                var date = new Date();
                date.setTime(date.getTime() + 24*60*60*1000);
                document.cookie = "token=" + token + ';expires=' + date.toUTCString() + ';path=/';
            }
        });
        return token;
    }

    // 토큰 검증함수
    function tokenVerification(email, jwtToken) {
        $.ajax({
            beforeSend : function (request) {
                request.setRequestHeader("Authorization", 'Bearer ' + jwtToken);
            },
            dataType : "json",
            async : false,
            url : "/process/verification?email="+email,
            contentType : 'application/json; charset=utf-8',
            //data : {email : 'user1@zerock.org'},
            success : function(res) {
                tokenCheck = res;
            }
        });
        return tokenCheck;
    }


