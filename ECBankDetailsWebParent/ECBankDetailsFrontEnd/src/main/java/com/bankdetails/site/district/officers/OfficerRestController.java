//package com.bankdetails.site.district.officers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.repository.query.Param;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class OfficerRestController {
//	
//	@Autowired OfficerService officerService;
//
////	@PostMapping("/officers/check_unique_email")
////	public String checkDuplicateEmail(Integer id, String email) {
////		return officerService.isEmailUnique(id, email) ? "OK" :"Duplicated";
////	}
//	
//	@PostMapping("/officers/check_unique_email")
//	public String checkDuplicateEmail(@Param("email") String  email) {
//		return officerService.isEmailUnique(email) ? "OK" :"Duplicated";
//	}
//}
