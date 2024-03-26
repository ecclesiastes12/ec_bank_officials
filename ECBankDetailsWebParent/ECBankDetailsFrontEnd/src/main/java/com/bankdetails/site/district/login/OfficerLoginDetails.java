package com.bankdetails.site.district.login;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bankdetails.entity.District;
import com.bankdetails.entity.Officer;

public class OfficerLoginDetails implements UserDetails{
	private static final long serialVersionUID = 1L;
	
	private Officer officer;
	
	public OfficerLoginDetails(Officer officer) {
		this.officer = officer;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return officer.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return officer.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return officer.isEnabled();
	}
	
	public Integer getOfficerDistrictId() {
		return this.officer.getDistrict().getId();
	}
	
	public Integer getOfficerId() {
		return this.officer.getId();
	}

	@Override
	public String toString() {
		return "OfficerLoginDetails [officer=" + officer + "]";
	}

	
	
}
