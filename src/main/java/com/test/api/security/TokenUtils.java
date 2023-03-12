package com.test.api.security;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class TokenUtils {

	//Creamos llave secreta
	private final static String ACCESS_TOKEN_SECRET ="$2a$14$kTYh435UfR/L0/2HW2Fae.hqLEsXGlc0tf3kc8sxYqCPT6FjOQ2kO";
	//TIEMPO DE VIDA UTIL OVALIDES SE LE VA A DAR 30 DIAS formula dia x hr x seg =30x24x3600
	private final static Long ACCESS_TOKEN_VALIDITY_SECONDS = 2_592_000L;
	
	//Metodo para crear el token
	public static String createToken(String nombre, String email) {
		//convertir los segundos a milisegundos para ser leidos mas facil
		Long expirationTime = ACCESS_TOKEN_VALIDITY_SECONDS * 1_000;
		Date expirationDate = new Date(System.currentTimeMillis()+expirationTime);
		
		//data aditional
		Map<String, Object> extra = new HashMap<>();
		extra.put("nombre", nombre);
		
		return Jwts.builder()
				.setSubject(email)
				.setExpiration(expirationDate)
				.addClaims(extra)
				.signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
				.compact();
	}
	
	//metodo parapasar el proceso deautorizacion de un usuario que esta intentando acceder a un endPoint mediante un token que esta en un formato plano
	public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
		try {
			Claims claims = Jwts.parserBuilder()
					.setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
					.build()
					.parseClaimsJws(token)
					.getBody();
			
			String email = claims.getSubject();
			
			return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());
		} catch (JwtException e) {
			// TODO: handle exception
			return null;
		}
		
	}
}
