package com.bankdetails.site.fieldofficer;

import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OfficerRestController {

	private OfficerServiceImpl officerService;

	public OfficerRestController(OfficerServiceImpl officerService) {
		super();
		this.officerService = officerService;
	}
	
	@PostMapping("/officers/check_unique_email")
	public String checkDuplicateEmail(@Param("email") String email) {
		return officerService.isEmailUnique(email) ? "OK" : "Duplicated";
	}
	
}
