$(document).ready(function() {

    var cookie = document.cookie.split(';');
    var token = '';

    for (var i = 0; cookie.length > i ; i++) {
        var getCookie = cookie[i];
        var _cookie = getCookie.split("=");

        if (_cookie[0] == ' token') {
            token = _cookie[1];
        }

    }

    $('#service_1').click(function() {
        $.ajax({
            beforeSend : function(request) {
                request.setRequestHeader("Authorization", 'Bearer ' + token);
            },
            dataType : "json",
            url : '/process/verification',
            contentType : 'application/json; charset=utf-8',
            success : function () {
                location.href = '/sample/service_1';
            }
        })
    });
    
    $('#service_2').click(function() {
        $.ajax({
            beforeSend : function(request) {
                request.setRequestHeader("Authorization", 'Bearer ' + token);
            },
            dataType : "json",
            url : '/process/verification',
            contentType : 'application/json; charset=utf-8',
            success : function () {
                location.href = '/sample/service_2';
            }
        })
    });

});