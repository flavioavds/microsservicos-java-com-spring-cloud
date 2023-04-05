package com.dev.hroauth.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.dev.hroauth.jwt.JwtEntryPoint;
import com.dev.hroauth.jwt.filter.JwtFilter;
import com.dev.hroauth.services.UserService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class MainSecurityConfig {
	
	@Autowired
	UserService userService;
	
	@Autowired
	JwtEntryPoint jwtEntryPoint;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	};
	
	@Bean
	public JwtFilter jwtFilter() {
		return new JwtFilter();
	};
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.cors().and().csrf().disable();
		http.authorizeHttpRequests().requestMatchers("/**").permitAll().anyRequest().authenticated();
		http.exceptionHandling().authenticationEntryPoint(jwtEntryPoint);
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);		
		return http.build();
	}

}
