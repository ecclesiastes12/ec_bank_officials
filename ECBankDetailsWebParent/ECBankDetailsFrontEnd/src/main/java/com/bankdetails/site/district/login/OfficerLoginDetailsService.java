package com.bankdetails.site.district.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.bankdetails.entity.Officer;
import com.bankdetails.site.district.officers.OfficersRepository;

public class OfficerLoginDetailsService implements UserDetailsService{

	@Autowired private OfficersRepository officerRepo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Officer officer = officerRepo.getOfficerByEmail(email);
		
		if(officer == null) {
			throw new UsernameNotFoundException("No Officer found with the email: " + email);
		}
		return new OfficerLoginDetails(officer);
	}

}
