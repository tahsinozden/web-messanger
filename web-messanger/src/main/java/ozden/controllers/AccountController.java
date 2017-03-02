package ozden.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AccountController {


	@Autowired
	HttpSession session;
	
	@PostMapping("/login")
	public String login(HttpServletRequest request, HttpServletResponse response){
		String currentUser = request.getParameter("user");
		session.setAttribute("currentUserName", currentUser);
		return "redirect:/index.html";
	}
	
	@GetMapping("/login")
	public void login(Model model){
		
	}
}
