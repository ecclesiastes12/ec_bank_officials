package com.bankdetails.admin;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bankdetails.admin.user.UserService;
import com.bankdetails.admin.user.login.LoginUserDetails;
import com.bankdetails.entity.District;
import com.bankdetails.entity.User;

@Controller
public class MainController {
	
	@Autowired UserService userService;
	
	@GetMapping("")
	public String viewHomePage(Model model, Authentication auth) {
		LoginUserDetails userDetails = (LoginUserDetails) auth.getPrincipal();
		District userDistrict = userDetails.getUserDistrict();
		
		List<User> usersInDistrict = userService.listUsersByDistrictId(userDistrict.getId());
		 
		int districtId = userDistrict.getId();; // Replace with your actual district ID
        int userCount = userService.countUsersByDistrictId(districtId);
        model.addAttribute("districtCode", usersInDistrict);
		model.addAttribute("totalUser", userCount);
		return "index";
	}

	
	@GetMapping("/login") 
	public String viewLoginPage() {
		//This line of code grabs the authenticated user or token (authentication object)
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		//checks the authentication object 
		//direct user to login page if user is not authenticated or user is a guest user
		if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			
			return "login";
		}
		return "redirect:/"; //prevent login page from displaying again once the user is login
	}
}
