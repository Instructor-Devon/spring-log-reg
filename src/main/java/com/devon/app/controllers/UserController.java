package com.devon.app.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.devon.app.models.User;
import com.devon.app.services.UserService;
import com.devon.app.validators.UserValidator;

@Controller
public class UserController {
	
	@Autowired
	private UserService uService;
	@Autowired
	private UserValidator validator;
	
	@GetMapping("/")
	public String Index(@ModelAttribute("buster") User user) {
		return "index.jsp";
	}
	// Registration
	@PostMapping("/")
	public String Register(@Valid @ModelAttribute("buster") User user, BindingResult result, HttpSession session) {
		
		// run our custom validations...
		validator.validate(user, result);
		
		if(result.hasErrors())
			return "index.jsp";
		
		// create user (by means of registerUser on service)
		User newUser = this.uService.registerUser(user);
		
		// we want to store userid in session
		session.setAttribute("userId", newUser.getId());
		
		// then redirect them to success
		return "redirect:/success";
	}
	
	@PostMapping("/login")
	public String Login(@RequestParam("email") String email, @RequestParam("password") String password, RedirectAttributes redirs, HttpSession session) {
		if(this.uService.authenticateUser(email, password)) {
			// put this user id in session
			User user = this.uService.getUserByEmail(email);
			session.setAttribute("userId", user.getId());
			
			// redirect to success
			return "redirect:/success";
		}
		
		redirs.addFlashAttribute("error", "Invalid Email/Password");
		return "redirect:/";
	}
	@GetMapping("/logout")
	public String Logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	@GetMapping("/success")
	public String Success(HttpSession session) {
		// prevent unauthenticated users from getting here!
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		
		return "success.jsp";
				
	}
}
