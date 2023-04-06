package com.dev.usermonolito.security.jwt;

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

import com.dev.usermonolito.security.services.UserDetailsServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthTokenFilter extends OncePerRequestFilter{
	
	private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = getToken(request);
		try {
			if(token != null && jwtUtils.validateJwtToken(token)) {
				String username = jwtUtils.getUsernameFromToken(token);				
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);				
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
