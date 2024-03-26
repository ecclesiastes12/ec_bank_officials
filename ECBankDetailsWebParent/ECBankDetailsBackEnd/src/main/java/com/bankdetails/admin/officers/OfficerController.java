package com.bankdetails.admin.officers;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bankdetails.admin.district.DistrictService;
import com.bankdetails.entity.District;
import com.bankdetails.entity.Officer;

@Controller
@RequestMapping("/officers")
public class OfficerController {

	private OfficerServiceImpl officerService;
	private DistrictService districtService;
	
	public OfficerController(OfficerServiceImpl officerService,DistrictService districtService) {
		this.officerService = officerService;
		this.districtService = districtService;
	}
	
	
	@GetMapping
	public String listOfficers(Model model) {
		
		List<Officer> listOfficers = officerService.listOfficers();
		model.addAttribute("listOfficers", listOfficers);
		
		return "officers/index";
		
		
	}
	
//	@GetMapping("/edit/{id}")
//	public String editOfficer(@PathVariable("id") Integer id, Model model,RedirectAttributes ra) {
//		try {
//			Officer officer = officerService.getOfficerById(id);
//			List<District> listDistricts = districtService.listDistricts();
//			model.addAttribute("listDistricts", listDistricts);
//			model.addAttribute("officer", officer);
//			return "officer/officer_registration_form";
//			
//		} catch (OfficerNotFoundException e) {
//			ra.addFlashAttribute("message", e.getMessage());
//		}
//		
//		return "redirect:/officers";
//	}
	
	
	
	
	
	//handler method that update user status
		@GetMapping("/{id}/enabled/{status}")
		public String updateUserEnabledStatus(
				@PathVariable("id") Integer id, 
				@PathVariable("status") boolean enabled,
				RedirectAttributes ra) {
			officerService.updateOfficerStatus(id, enabled);
			
			//condition to enabled and disabled status
			String status = enabled ? "enabled" : "disabled";
			
			String message = "This user ID " + id + " has been " + status;
			ra.addFlashAttribute("message", message);
			
			return "redirect:/officers";
			
//			if(authentication != null && authentication.getAuthorities().stream().anyMatch(
//					auth -> auth.getAuthority().equals("Admin"))) {
	//
//				return "redirect:/users";
//			}else {
//				// Redirect to the district-specific page for normal admin user
//	            return "redirect:/users/userDistrict";
//			}
			
			//  return userPageRedirect(authentication);
		}
	
		
		
		private String userPageRedirect(Authentication authentication) {
			if(authentication != null && authentication.getAuthorities().stream().anyMatch(
					auth -> auth.getAuthority().equals("Admin"))) {

				return "redirect:/users";
			}else {
				// Redirect to the district-specific page for normal admin user
	            return "redirect:/users/userDistrict";
			}
		}
		
		
		
}
