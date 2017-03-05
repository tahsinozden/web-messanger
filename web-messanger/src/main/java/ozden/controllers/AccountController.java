package ozden.controllers;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ozden.entities.User;
import ozden.services.UserService;

@Controller
public class AccountController {
	private static final Logger log = LoggerFactory.getLogger(AccountController.class);
	
	@Autowired
	private UserService userService;

	@Autowired
	HttpSession session;
	
	@PostMapping("/login")
	public String login(Model model,
						HttpServletRequest request, 
						HttpServletResponse response,
						@RequestBody final User usr) throws Exception{
 
    	if (usr == null){
    		throw new ServletException("Provide a user!");
    	}
    	
    	String userName = usr.getUserName();
    	String password = usr.getPassword();
    	
    	// check username and password
    	if (!userService.login(userName, password)){
        	throw new ServletException("User name or password wrong");
    	}
    	
    	log.debug("setting the current user as " + userName);
		session.setAttribute("currentUserName", userName);
		return "redirect:/";
	}
	
	// understand why it works for redirection but the others dont work
	// it is probably I call /login url directly from browser, and browser sets the headers correctly
	// try to implement the same with jquery post and get with proper headers
	@GetMapping("/login")
	public String login(Model model){
		// check if there is a logged user or not
		if (session.getAttribute("currentUserName") == null){
			// Login.html
			return "Login";
		}
//		return "redirect:/";
		return "index";
	}
	
	@PostMapping("/logout")
	public String logout(Model model){
		session.removeAttribute("currentUserName");
		// redirect to home page
		return "redirect:/"; 
	}
	
//	@RequestMapping(value="/create-user", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
//	public String createUser(@RequestParam String username, 
//							 @RequestParam String password){
//		return "user created!";
//	}
}
