//package com.lmp.lunchmenu.util;
//
//import java.security.Key;
//import java.util.Date;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.security.Keys;
//
//public class JwtUtil {
//
//	public static String createJwt(String key, String secretKey, long expiredMs) {
//		
//		// 그냥 문자열로 넣을 경우 deprecated 나온다.
//		Key secretKeyChange = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//		
//		Claims claims = Jwts.claims();
//		claims.put("key", key);
//		
//		return Jwts.builder()
//					.setIssuedAt(new Date(System.currentTimeMillis()))
//					.setExpiration(new Date(System.currentTimeMillis() + expiredMs))
//					.signWith(secretKeyChange) // signWIth(SignatureAlgorithm.HS256, secretKey)
//					.compact();
//	}
//}
