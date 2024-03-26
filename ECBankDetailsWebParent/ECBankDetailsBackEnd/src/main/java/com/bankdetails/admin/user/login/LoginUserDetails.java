package com.bankdetails.admin.user.login;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bankdetails.entity.District;
import com.bankdetails.entity.Role;
import com.bankdetails.entity.User;

public class LoginUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private User user;
	private District district;
	
	public LoginUserDetails(User user,District district) {
		this.user = user;
		this.district = district;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// get set of user roles
		Set<Role> roles = user.getRoles();
		
		//create an array list object of granted authorities
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		//loop through the roles
		for (Role role : roles) {
			//add role name to the authorities
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getEmail();
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
		return user.isEnabled();
	}

	//method that get the user district
	public District getUserDistrict() {
		return district;
	}

	@Override
	public String toString() {
		return "LoginUserDetails [user=" + user + ", district=" + district + "]";
	}
	
	
}
