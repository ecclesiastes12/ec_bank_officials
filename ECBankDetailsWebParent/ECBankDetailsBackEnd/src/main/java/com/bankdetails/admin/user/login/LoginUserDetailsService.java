package com.bankdetails.admin.user.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.bankdetails.admin.user.UserRepository;
import com.bankdetails.entity.District;
import com.bankdetails.entity.User;



public class LoginUserDetailsService implements UserDetailsService{

	@Autowired UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		User user = userRepo.getUserByEmail(email);
		District district = user.getDistrict();
		
		//check if email is not null
		if(user != null && district != null) {
			return new LoginUserDetails(user, district);
		}
		
		throw new UsernameNotFoundException("Could not find user with email " + email);
	}
	
	
}
