package com.dev.hroauth.jwt.filter;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.dev.hroauth.jwt.JwtProvider;
import com.dev.hroauth.services.UserDetailsServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter{
	
	private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

	@Autowired
	JwtProvider jwtProvider;
	
	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;
	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = getToken(request);
		try {
			if(token != null && jwtProvider.validateJwtToken(token)) {
				String username = jwtProvider.getUsernameFromToken(token);				
				UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);				
				UsernamePasswordAuthenticationToken authentication = 
						new UsernamePasswordAuthenticationToken(userDetails.getUsername(), 
																null, 
																userDetails.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
			
		} catch (UsernameNotFoundException e) {
			logger.error("filter blocked request: {}", e);
		}
		filterChain.doFilter(request, response);
		
	}
	
	private String getToken(HttpServletRequest req) {
		String header = req.getHeader("Authorization");
		if(header != null && header.startsWith("Bearer "))
			return header.replace("Bearer ", "");
		return null;
	}
	
}
