package com.bankdetails.site;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.bankdetails.site.district.login.OfficerLoginDetailsService;

@Configuration
public class WebSecurityConfig {
	
	@Bean
	UserDetailsService userDetailsService() {
		return new OfficerLoginDetailsService();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() { //for password encryption
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider;
	}
	

	@Bean SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authenticationProvider(authenticationProvider());
		
		//http.csrf((csrf) -> csrf.disable());
		http.authorizeHttpRequests((authz) -> authz
			
			.requestMatchers("/officers/new").permitAll()
			.requestMatchers("/officers/index","/officers/bank").authenticated()
			.anyRequest().authenticated()
				
				)
			.formLogin(form -> form
					.loginPage("/login")
					.usernameParameter("email")
					.permitAll());
			
		
		return http.build();
	}
	

	@Bean WebSecurityCustomizer webSecurityCustomizer() {
	return (web) -> web.ignoring().requestMatchers("/css/**", "/webjars/**","/js/**","/favicon.ico","/images/**");
	}
}
