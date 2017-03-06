package ozden.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ozden.entities.User;
import ozden.services.UserService;

@RestController
@RequestMapping("/api")
public class AccountRestController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="create-user", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public User createUser(@RequestParam String username, 
							 @RequestParam String password) throws ServletException{
		User usr = new User();
		usr.setUserName(username);
		usr.setPassword(password);
		usr.setStatusFlag(1);
		usr = userService.createAccount(usr);
		usr.setPassword("****");
		return usr;
	}
	
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public LoginResponse login(@RequestBody final User usr)
        throws Exception {
    	if (usr == null){
    		throw new ServletException("Provide a user!");
    	}
    	
    	String userName = usr.getUserName();
    	String password = usr.getPassword();
    	
    	// check username and password
    	if (!userService.login(userName, password)){
        	throw new ServletException("User name or password wrong");
    	}

		// TODO: add a new field Role for User entity
//        return new LoginResponse(Jwts.builder().setSubject(userName)
//            .claim("roles", "user").setIssuedAt(new Date())
//            .signWith(SignatureAlgorithm.HS256, "secretkey").compact());
    	
    	return new LoginResponse("token");
    }

    
    @RequestMapping(value = "check-user", method = RequestMethod.POST)
    public Boolean checkUser(@RequestBody final User usr) throws ServletException{
    	if (usr == null){
    		throw new ServletException("Provide a user!");
    	}
    	
    	String userName = usr.getUserName();
    	// return invalid data exc.
    	return userService.checkUser(userName);
    }
    
    @GetMapping("all-users")
    public List<User> getAllUsers(){
    	List<User> users = userService.getAllUsers();
    	users.stream()
    		.forEach(usr -> usr.setPassword("****"));
//    		.collect(Collectors.toList());
    	
    	return users;
    }
    
    @SuppressWarnings("unused")
    private static class UserLogin {
        public String name;
        public String password;
    }

    @SuppressWarnings("unused")
    private static class LoginResponse {
        public String token;

        public LoginResponse(final String token) {
            this.token = token;
        }
    }
    
    @SuppressWarnings("unused")
    private static class UserCreate {
        public String username;
        public String password;
    }
}
