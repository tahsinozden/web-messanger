
function createUser(){
	var userName = $("#crt-username").val();
	var password = $("#crt-password").val();
	
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
	window.location = "/";
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


