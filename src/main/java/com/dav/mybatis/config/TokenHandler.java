package com.dav.mybatis.config;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

// TODO: Auto-generated Javadoc
/**
 * The Class TokenHandler.
 */
@Component()
public class TokenHandler {

	/** The secret. */
	private final String secret= "DAV_SERVER";

	/**
	 * Parses the user from token.
	 *
	 * @param token the token
	 * @return the string
	 */
	public String parseUserFromToken(String token) {
		String username = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
		return username;
	}

	/**
	 * Creates the token for user.
	 *
	 * @param username the username
	 * @return the string
	 */
	public String createTokenForUser(String username) {
		Date now = new Date();
		Date expiration = new Date(now.getTime() + TimeUnit.HOURS.toMillis(1l));

		return Jwts.builder()
				.setId(UUID.randomUUID().toString())
				.setSubject(username)
				.setIssuedAt(now)
				.setExpiration(expiration)
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();
	}
}
