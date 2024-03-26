//package com.bankdetails.site.district.officers;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import com.bankdetails.entity.District;
//import com.bankdetails.entity.Officer;
//import com.bankdetails.entity.OfficerRole;
//import com.bankdetails.entity.UserBankDetails;
//import com.bankdetails.site.district.DistrictService;
//import com.bankdetails.site.district.login.OfficerLoginDetails;
//import com.bankdetails.site.district.officersrole.OfficerRoleService;
//
//import jakarta.servlet.http.HttpServletRequest;
//
//@Controller
//@RequestMapping("/officers")
//public class OfficerController1 {
//
//	@Autowired private OfficerService officerService;
//	@Autowired private DistrictService districtService;
//	@Autowired private OfficerRoleService officerRoleService;
//	
//	
//	
//	
//	@GetMapping("/bank")
//	public String viewOfficerBankForm(Model model,Authentication auth) {
//		OfficerLoginDetails loginDetails = (OfficerLoginDetails) auth.getPrincipal();
//		Integer officerIdInDB = loginDetails.getOfficerId();
//	    System.out.println("officer login details: " + loginDetails);
//	    
//		UserBankDetails bankInfo = new UserBankDetails();
//		model.addAttribute("officerBankInfo", bankInfo);
//		model.addAttribute("officerIdInDB", officerIdInDB);
//		return "officer/user_bankdetails_form";
//	}
//	
//	
//	@GetMapping("/new")
//	public String viewOfficerForm(Model model) {
//				
//		List<District> listDistricts = districtService.listDistrict();
//		List<OfficerRole> listOfficerRoles =  officerRoleService.listOfficerRoles();
//		
//		Officer officer = new Officer();
//		
//		model.addAttribute("officerForm", officer);
//		model.addAttribute("listDistricts", listDistricts);
//		model.addAttribute("listOfficerRoles", listOfficerRoles);
//		
//		return "officer/officer_form";
//		
//	}
//	
//	@PostMapping("/save")
//	public String saveOfficer(Officer officer,RedirectAttributes ra) {
//		
//		officerService.saveOfficer(officer);
//		ra.addFlashAttribute("message", "Registration successfully!");
//		return "redirect:/officer/success";
//	}
//	
//	
////	@GetMapping("/edit/{id}")
////	public String editOfficer(@PathVariable("id") Integer id, Model model,RedirectAttributes ra) {
////		try {
////			Officer officer = officerService.getOfficerId(id);
////			List<District> listDistricts = districtService.listDistrict();
////			model.addAttribute("listDistricts", listDistricts);
////			model.addAttribute("officer", officer);
////			return "officer/officer_registration_form";
////			
////		} catch (OfficerNotFoundException e) {
////			ra.addFlashAttribute("message", e.getMessage());
////		}
////		
////		return "redirect:/officers";
////	}
//}
