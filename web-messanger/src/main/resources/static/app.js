var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    $("#send").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
	var sender = $("#sender").val();
	if (sender == undefined || sender == ""){
		return;
	}
	
    var socket = new SockJS('/messages-endpoint');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        $.cookie("loggedUser", $("#sender").val());
        $("#sender").prop("disabled", true);
        stompClient.subscribe('/topic/messages', function (greeting) {
            showMessages(JSON.parse(greeting.body));
            console.log(JSON.parse(greeting.body));
        });
    });
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    setConnected(false);
    $.removeCookie("loggedUser");
    $.removeCookie("receiver");
    $("#sender").prop("disabled", false);
    console.log("Disconnected");
}

function sendMessage() {
	// check if the user connected to the web socket
	var isConnected = $("#connect").prop("disabled");
	if (isConnected == false){
		alert("connect to the service!");
		return;
	}
	
	var sender = $("#sender").val();
	var receiver = $("#receiver").val()
	console.log("receiver is " + receiver);
	// check if receiver is null
	if (receiver == null || receiver == undefined || receiver == ""){
		alert("input receiver!")
		return;
	}
	
	// set receiver cookie
	$.cookie("receiver", receiver);
	
	var displayMessage = "";
	displayMessage = "<tr><td style='text-align: right'><small class='sender-receiver'>[sent to " + $("#receiver").val() + "]	</small><strong>" + $("#messagecontent").val() + "</strong</td></tr>";
    $("#messages").append(displayMessage);
    
    stompClient.send("/app/message", {}, 
    		JSON.stringify(
    				{'sender': sender,
    				 'receiver': receiver,
    				 'messageContent': $("#messagecontent").val()
    				}
    				));
}

function showMessages(message) {
	var loggerUser = $.cookie("loggedUser");
	var receiver = $.cookie("receiver");
	var displayMessage = "";
	
	if (loggerUser == message.receiver){
		displayMessage = "<tr><td style='text-align: left'><small class='sender-receiver'>[sent by " + message.sender + "]	</small><strong>" + message.messageContent + "</strong></td></tr>";
	}
    $("#messages").append(displayMessage);
}

function logout(){
	$.post("/logout", 
			function(res){
				console.log("user logged out!");
			}
	)
	.fail(function(error){
		
	})
	window.location = "/";
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendMessage(); });
    $("#btnLogout").click(function(){ logout(); });
});

