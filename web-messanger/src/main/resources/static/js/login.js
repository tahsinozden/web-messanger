
function createUser(){
	var userName = $("#crt-username").val();
	var password = $("#crt-password").val();
	var repassword = $("#crt-repassword").val();
	
	if (userName == "" || password == ""){
		alert("empty username or password!");
		return;
	}
	
	if (password != repassword){
		alert("passwords not match!");
		return;
	}
	
	$.post("/api/create-user", 
			{ 'username': userName, 'password': password}, 
			function(data){
				console.log(data);
				alert("User created!");
				window.location = "/";
			}
	)
	.fail(			
			function(res){
				alert(res.responseJSON.message);
				console.log(res);
				$("#crt-username").val("");
				$("#crt-password").val("");
				$("#crt-repassword").val("");
	});
	
}

function login(){
	var userName = $("#username").val();
	var password = $("#password").val();
	
	if (userName == "" || password == ""){
		alert("empty username or password!");
		return;
	}
	
	$.ajax({
        url : "/login",
        type: "POST",
        data: JSON.stringify(
            {"userName": userName, "password": password}
        ),
        contentType: "application/json; charset=utf-8",
        dataType   : "json",
        success    : function(res){
            console.log(res);
//        	window.location = "/";
        },
        fail	   : function(error){
        	console.log(error);
        }
    });
	
	console.log("login");
//	window.location = "/";
	// set timeout for server to be able to set all session attributes before redirecting to main page
	setTimeout(function(){window.location = "/";}, 100);
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
    
    $("#btnCreateUserForm").click(function(){ createUser(); });
    $("#btnLogin").click(function(){ login(); });
    $("#btnLogout").click(function(){ logout(); });
});


