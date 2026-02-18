package com.raj.security;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTUtils {

	private String secretKey ="hdgvcrqyrvcovrrcyytedCGKCLeoufpwCHIkefULefpfgCIEVCIRG";
	private Key key=Keys.hmacShaKeyFor(secretKey.getBytes());
	
	public String generateKey(String id) {
		return Jwts.builder()
			  .setSubject(id)
			  .setIssuedAt(new Date())
			  .setExpiration(new Date(System.currentTimeMillis()+1000*60*4))
			  .signWith(key, SignatureAlgorithm.HS256)
			  .compact();
		
	}
	
	public String readToken(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
	}
}
