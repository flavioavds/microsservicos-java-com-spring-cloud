package com.dev.hroauth.jwt;

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

import com.dev.hroauth.entities.User;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

@Component
public class JwtProvider{
	
	private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);
	
	@Value("${jwt.secret}")
	private String jwtSecret;
	
	@Value("${jwt.expiration}")
	private int expiration;
	
	@Value("${jwt.issuer")
	private String issuer;
		
	public String getUserNameFromToken(String token) {
		return Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody().getSubject();
	}
	
	
	public boolean validateJwtToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody();
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
	
	public String generateToken(Authentication authentication) {
		User user = (User) authentication.getPrincipal();
		return Jwts.builder()
				.setHeaderParam("typ", "JWT")
				.setIssuer(issuer)
				.setSubject(user.getUsername())
				.setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + expiration))
				.signWith(getKey(), SignatureAlgorithm.HS512)
				.claim("roles", getRoles(user))
				.claim("curso", "microserviços")
				.compact();
	}
	
	private List<String> getRoles(User user){
		return user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
	}
	
	private Key getKey() {
		byte [] secretBytes = Decoders.BASE64URL.decode(jwtSecret);
		return Keys.hmacShaKeyFor(secretBytes);
	}
	
}