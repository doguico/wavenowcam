package com.wavenowcam.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.UUID;

/**
 *
 * @author guidocorazza
 */
public class JWTUtil {

    private static final Long EXPIRATION_TIME = new Long(2 * 60 * 1000); // 2 MINUTES
    private static final String SECRET = "Prueb@"; // TODO: Leerlo desde un archivo externo

    public static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER_STRING = "Authorization";

    public static String build(String username) {
        Date now = new Date();

        String token = Jwts.builder()
                .setId(UUID.randomUUID().toString()) // TODO: Sacar?
                .setSubject(username)
                .setIssuedAt(now) // TODO: Sacar?
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                //.compressWith(CompressionCodecs.DEFLATE) // uncomment to enable token compression
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes()) // TODO: Probar con SECRET solo tmb
                .compact();

        return token;
    }

    public static String parse(String token) {
        String username = Jwts.parser()
                .setSigningKey(SECRET.getBytes()) // TODO: Probar con SECRET solo tmb
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody()
                .getSubject();

        return username;

    }
}
