package com.example;

import java.util.Date;
import javax.crypto.spec.SecretKeySpec;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTDemo {
    public static void main(String[] args) {

        String secret = "This_is_a_secret_keyasadasdkasdkajsdhkasjdhkalsjhdlkasdaksjdaslkdas";

        String jwtToken = generateJwtToken(secret);
        System.out.println(jwtToken);
        parseJwt(jwtToken, secret);
    }

    private static String generateJwtToken(String secret) {

        long currentTimeInMillis = System.currentTimeMillis();
        long expiryTimeInMillies = 60 * 60 * 1000;

        Date issuedAt = new Date(currentTimeInMillis);
        Date expiryAt = new Date(currentTimeInMillis + expiryTimeInMillies);

        Claims claims = Jwts.claims();
        claims.put("name", "karthikeyan");
        claims.put("role", "admin");

        return Jwts.builder()
                .setExpiration(expiryAt)
                .signWith(new SecretKeySpec(secret.getBytes(), SignatureAlgorithm.HS512.getJcaName()),
                        SignatureAlgorithm.HS512)
                .setClaims(claims)
                .setIssuedAt(issuedAt)
                .setExpiration(expiryAt)
                .compact();
    }

    public static void parseJwt(String jwtToken, String secret) {

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(new SecretKeySpec(secret.getBytes(), SignatureAlgorithm.HS512.getJcaName()))
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();

        System.out.println(claims.toString());
        System.out.println(claims.get("name"));
    }
}
