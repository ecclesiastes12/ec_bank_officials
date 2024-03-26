package com.bankdetails.site.bankdetails;
//package com.bankdetails.admin.user.bankdetails;
//
//import java.util.List;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import com.bankdetails.entity.UserBankDetails;
//
//@Controller
//@RequestMapping("/bankdetails")
//public class UserBankDetailsController2 {
//
//	private UserBankDetailsServiceImpl bankDetailsService;
//	
//	public UserBankDetailsController2(UserBankDetailsServiceImpl bankDetailsService) {
//		this.bankDetailsService = bankDetailsService;
//	}
//	
//	
//	@GetMapping
//	public String listBankDetails(Model model) {
//		List<UserBankDetails> listBankInfo = bankDetailsService.listUserBankDetails();
//		model.addAttribute("listBankInfo", listBankInfo);
//		return "bank_details/index";
//	}
//	
//	
//	@GetMapping("/new")
//	public String bankDetailsForm(Model model) {
//		UserBankDetails bankInfo = new UserBankDetails();
//		model.addAttribute("bankInfo", bankInfo);
//		model.addAttribute("pageTitle", "User Bank Information Form");
//		
//		return "bank_details/user_bankdetails_form";
//	}
//	
//	@PostMapping("/save")
//	public String addBankDetails(UserBankDetails userBankDetails, RedirectAttributes ra) {
//		
//		bankDetailsService.addUserBankDetails(userBankDetails);
//		ra.addFlashAttribute("message", "User bank information saved succesfully");
//		
//		return "redirect:/bankdetails";
//	}
//	
//
//	@GetMapping("/edit/{id}")
//	public String updateBankDetails(Model model,
//			@PathVariable("id") Integer id) throws UserBankDetailsNotFoundException {
//		UserBankDetails userBankDetailsId = bankDetailsService.getBankDetailsById(id);
//		model.addAttribute("bankInfo", userBankDetailsId);
//		return "bank_details/user_bankdetails_form";
//	}
//	
//	@GetMapping("/delete/{id}")
//	public String deleteBankDetails(@PathVariable("id") Integer id,RedirectAttributes ra) {
//		try {
//			bankDetailsService.deleteUserBankDetails(id);
//		} catch (UserBankDetailsNotFoundException e) {
//			ra.addFlashAttribute("message", e.getMessage());
//		}
//		
//		return "redirect:/bankdetails";
//	}
//}
