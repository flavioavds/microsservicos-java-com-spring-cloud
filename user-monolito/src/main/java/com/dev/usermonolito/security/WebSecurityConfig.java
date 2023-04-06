package com.dev.usermonolito.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.dev.usermonolito.security.jwt.AuthEntryPointJwt;
import com.dev.usermonolito.security.jwt.AuthTokenFilter;
import com.dev.usermonolito.security.services.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {
	
	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	AuthEntryPointJwt authEntryPointJwt;
	
	@Autowired
	AuthTokenFilter authTokenFilter;
	
	AuthenticationManager authenticationManager;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
		builder.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder);
		authenticationManager = builder.build();
		http.authenticationManager(authenticationManager);
        http.cors().and().csrf().disable();
        http.authorizeHttpRequests().requestMatchers("/**").permitAll().anyRequest().authenticated();
        http.exceptionHandling().authenticationEntryPoint(authEntryPointJwt);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);		
		return http.build();
	}

}
