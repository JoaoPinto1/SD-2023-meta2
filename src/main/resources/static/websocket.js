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
    $("#messages").html("");
}

function connect() {
    var socket = new SockJS('/my-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/messages', function (message) {
            var parsedMessage = JSON.parse(message.body)
            showMessage(parsedMessage.barrels,parsedMessage.downloaders,parsedMessage.searches);
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


function sendMessage() {
    stompClient.send("/app/message", {}, JSON.stringify({'content': $("#message").val()}));
}

function showMessage(barrels,downloaders,searches) {
    $("#messages").empty()
    $("#messages").append("<p><b>Downloaders:</b></p>" + "<tr><td>" + downloaders + "</td></tr><p><b>Barrels:</b></p><tr><td>" + barrels + "</td></tr><p><b>Top searches:</b></p><tr><td>"+searches+"</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendMessage(); });
});

