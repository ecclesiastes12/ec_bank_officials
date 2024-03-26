package com.bankdetails.admin.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {

	@Autowired
	private UserServiceImpl userService;
	
	//method that check for duplicate email
	@PostMapping("/users/check_email")
	public String checkDuplicateEmail(Integer id, String email) {
		return userService.isEmailUnique(id,email) ? "OK" :"Duplicated";
	}
}
