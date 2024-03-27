package com.bankdetails.site.bankdetails;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bankdetails.entity.District;
import com.bankdetails.entity.Officer;
import com.bankdetails.entity.UserBankDetails;
import com.bankdetails.site.district.DistrictService;
import com.bankdetails.site.district.login.OfficerLoginDetails;
import com.bankdetails.site.fieldofficer.OfficerServiceImpl;

@Controller
@RequestMapping("/bankinfo")
public class UserBankDetailsController {

	private UserBankDetailsServiceImpl bankDetailsService;
	private OfficerServiceImpl officerService;
	private DistrictService districtService;
	

	public UserBankDetailsController(UserBankDetailsServiceImpl bankDetailsService, OfficerServiceImpl officerService,
			DistrictService districtService) {
		this.bankDetailsService = bankDetailsService;
		this.officerService = officerService;
		this.districtService = districtService;
	}


	@GetMapping
	public String listBankDetails(Model model,Authentication auth) {
		OfficerLoginDetails loginDetails = (OfficerLoginDetails) auth.getPrincipal();
	    Integer officerIdInDB = loginDetails.getOfficerId();
		
    	UserBankDetails listBankInfo = bankDetailsService.getOfficerIdInUserBankDetails(officerIdInDB);
    	model.addAttribute("listBankInfo", listBankInfo);
    
	    
		
		return "bankinfo/index";
	}
	
	
	@GetMapping("/new")
	public String bankDetailsForm(Model model) {
		UserBankDetails bankInfo = new UserBankDetails();
		model.addAttribute("bankInfo", bankInfo);
		model.addAttribute("pageTitle", "User Bank Information Form");
		
		return "bankinfo/user_bankdetails_form";
	}
	
//	@PostMapping("/preview")
//	public String previewBankDetails(UserBankDetails userBankDetails, Model model) {
//	    model.addAttribute("bankInfo", userBankDetails);
//	    return "bank_details/preview";
//	}
	
	@PostMapping("/save")
	public String addBankDetails(UserBankDetails userBankDetails, RedirectAttributes ra, Authentication auth) throws Exception {
		
		OfficerLoginDetails loginDetails = (OfficerLoginDetails) auth.getPrincipal();
	    Integer officerIdInDB = loginDetails.getOfficerId();
	    Integer officerDistrictIdInDB = loginDetails.getOfficerDistrictId();
	   
	    System.out.println("Officer district ID : " + officerDistrictIdInDB);
	    
	    Officer officer = officerService.getOfficerId(officerIdInDB);
	    District district = districtService.getDistrictId(officerDistrictIdInDB);
	    		
		//System.out.println(bankDetailsService.addUserBankDetails(userBankDetails));
		System.out.println("Officer district: " + district);
		
		
		userBankDetails.setOfficer(officer);
		userBankDetails.setDistrict(district);
		
		bankDetailsService.addUserBankDetails(userBankDetails);
		
		ra.addFlashAttribute("message", "User bank information saved succesfully");
		
		return "redirect:/bankinfo";
	}
	
	
//	@PostMapping("/preview")
//    public String previewData( Model model) {
//		UserBankDetails userBankDetails = new UserBankDetails();
//        model.addAttribute("bankInfo", userBankDetails);
//        return "redirect:/preview";
//    }
//
//    @PostMapping("/save")
//    public String saveData(UserBankDetails userBankDetails) {
//        bankDetailsService.addUserBankDetails(userBankDetails);
//        return "redirect:/success";
//    }
	

	@GetMapping("/edit/{id}")
	public String updateBankDetails(Model model,
			@PathVariable("id") Integer id) throws UserBankDetailsNotFoundException {
		UserBankDetails userBankDetailsId = bankDetailsService.getBankDetailsById(id);
		model.addAttribute("bankInfo", userBankDetailsId);
		return "bankinfo/user_bankdetails_form";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteBankDetails(@PathVariable("id") Integer id,RedirectAttributes ra) {
		try {
			bankDetailsService.deleteUserBankDetails(id);
		} catch (UserBankDetailsNotFoundException e) {
			ra.addFlashAttribute("message", e.getMessage());
		}
		
		return "redirect:/bankinfo";
	}
}
