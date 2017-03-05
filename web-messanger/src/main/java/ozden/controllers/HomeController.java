package ozden.controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import ozden.entities.User;
import ozden.services.UserService;

@Controller
public class HomeController {
	private static final Logger log = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("/")
	public String home(Model model){
		log.info("checking user is logged or not!");
		// check if there is a logged user or not
		if (session.getAttribute("currentUserName") == null){
			log.info("no user logged!");
			// Login.html
			return "Login";
		}
		return "index";
	}
	
	
//	@PostMapping("/login")
//	public String login(Model model,
//						HttpServletRequest request, 
//						HttpServletResponse response,
//						@RequestBody final User usr) throws Exception{
// 
//    	if (usr == null){
//    		throw new ServletException("Provide a user!");
//    	}
//    	
//    	String userName = usr.getUserName();
//    	String password = usr.getPassword();
//    	
//    	// check username and password
//    	if (!userService.login(userName, password)){
//        	throw new ServletException("User name or password wrong");
//    	}
//    	
////    	log.debug("setting the current user as " + userName);
//		session.setAttribute("currentUserName", userName);
//		return "redirect:/";
//	}
	
}
