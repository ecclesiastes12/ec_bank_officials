//package com.bankdetails.site.district.officers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class OfficerRestController1 {
//
//	@Autowired private OfficerService officerService;
//	
//	//method that check for duplicate email
//	@PostMapping("/officers/check_unique_email")
//	public String checkDuplicateEmail(Integer id, String email) {
//		return officerService.isEmailUnique(id,email) ? "OK" :"Duplicated";
//	}
//}
