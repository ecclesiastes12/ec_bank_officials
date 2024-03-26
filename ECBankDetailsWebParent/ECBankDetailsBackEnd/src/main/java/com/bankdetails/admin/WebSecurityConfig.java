package com.bankdetails.admin;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.bankdetails.admin.user.login.LoginUserDetailsService;

@Configuration
public class WebSecurityConfig {
	
	@Bean
	UserDetailsService userDetailsService() {
		return new LoginUserDetailsService();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() { //for password encryption
		return new BCryptPasswordEncoder();
	}
	
	//method that configures the authentication provider
		//DaoAuthenticationProvider tell spring security that
		//authentication will be base on the database
	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider;
	}
	
	

	@Bean SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authenticationProvider(authenticationProvider());
		
		//http.csrf((csrf) -> csrf.disable())
			http.authorizeHttpRequests((authz) -> authz
					.requestMatchers("/css/**", "/webjars/**","/js/**","/favicon.ico","/images/**").permitAll()
					.requestMatchers("/districts/**").hasAuthority("Admin")
					.requestMatchers("/users/**", "/bankdetails/**","/officers/**").hasAnyAuthority("Admin","District_Officer","Returning_Officer")
					.anyRequest().authenticated())
            .formLogin(form -> form
                .loginPage("/login") // Specify your custom login page URL
                .usernameParameter("email")//The HTTP parameter to look for the username when performing authentication. Defaultis "username".
                //.defaultSuccessUrl("/dashboard") // Redirect after successful login
                .permitAll())
            .logout(logout -> logout
              //  .logoutSuccessUrl("/login")
                .permitAll());
		
		return http.build();
	}
	
//	@Bean SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http.authenticationProvider(authenticationProvider());
//		
//		//http.csrf((csrf) -> csrf.disable())
//			http.authorizeHttpRequests((authz) -> authz
//					.requestMatchers("/districts/**","/users/**", "/bankdetails/**").permitAll())
//        
//		
//		return http.build();
//	}
//	
	
	
//	@Bean WebSecurityCustomizer webSecurityCustomizer() {
//		return (web) -> web.ignoring().requestMatchers("/css/**", "/webjars/**","/js/**","/favicon.ico","/images/**");
//	}
}
