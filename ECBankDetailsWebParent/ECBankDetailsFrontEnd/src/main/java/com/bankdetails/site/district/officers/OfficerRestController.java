package com.bankdetails.site.district.officers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OfficerRestController {
	
	@Autowired OfficerService officerService;

	@PostMapping("/officers/check_unique_email")
	public boolean checkDuplicateEmail(Integer id, String email) {
		return officerService.isEmailUnique(id, email);
	}
}
