package com.dev.usermonolito.security.jwt;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.dev.usermonolito.security.services.UserDetailsImpl;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

@Component
public class JwtUtils {
	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
	
	@Value("${app.jwt.secret}")
	private String jwtSecret;
	
	@Value("${app.jwt.expiration}")
	private Long jwtExpirationMs;
	
	@Value("${app.jwt.name}")
	private String jwtCookie;
	
	public String getUsernameFromToken(String token) {
		return Jwts.parserBuilder().setSigningKey(getKey(jwtSecret))
				.build().parseClaimsJws(token).getBody().getSubject();
	}
	
	public String generateToken(Authentication authentication) {
		UserDetailsImpl mainUser = (UserDetailsImpl) authentication.getPrincipal();
		return Jwts.builder()
				.setHeaderParam("typ", "JWT")
				.setIssuer(jwtCookie)
				.signWith(getKey(jwtSecret))				
				.setSubject(mainUser.getUsername())
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
				.claim("roles", getRoles(mainUser))
				.claim("estudos", "microsservicos")
				.compact();
	}
		
	public boolean validateJwtToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(getKey(jwtSecret))
			.build().parseClaimsJws(token).getBody();
			return true;
		} catch (SignatureException e) {
			logger.error("Invalid JWT signature: {}", e.getMessage());
		} catch (MalformedJwtException e) {
			logger.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			logger.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			logger.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error("JWT claims strring is empty: {}", e.getMessage());
		} catch (Exception e) {
			logger.error("Fail token: {}", e.getMessage());
		}
		return false;
	}
	
	private List<String> getRoles(UserDetailsImpl user){
		return user.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());
	}
	
	private Key getKey(String secret) {
		byte [] secretBytes = Decoders.BASE64URL.decode(jwtSecret);
		return Keys.hmacShaKeyFor(secretBytes);
	}

}
