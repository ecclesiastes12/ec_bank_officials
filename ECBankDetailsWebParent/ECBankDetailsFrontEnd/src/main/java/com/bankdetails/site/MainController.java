package com.bankdetails.site;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bankdetails.entity.Officer;
import com.bankdetails.entity.UserBankDetails;
import com.bankdetails.site.bankdetails.UserBankDetailsServiceImpl;
import com.bankdetails.site.district.login.OfficerLoginDetails;

@Controller
public class MainController {

	@Autowired
	private UserBankDetailsServiceImpl bankDetailsService;
	
	@GetMapping("")
	public String viewHomePage(Model model, Authentication auth) {
		
		OfficerLoginDetails loginDetails = (OfficerLoginDetails) auth.getPrincipal();
	    Integer officerIdInDB = loginDetails.getOfficerId();
	    
	    	UserBankDetails listBankInfo = bankDetailsService.getOfficerIdInUserBankDetails(officerIdInDB);
	    	model.addAttribute("listBankInfo", listBankInfo);
	   
		return "bankinfo/index";
	}
	
	
	@GetMapping("/login")
	public String viewLoginPage() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return "login";
		}
		
		return "redirect:/";
	}
	
	
	
}
