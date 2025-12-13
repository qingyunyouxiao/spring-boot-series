package com.example.demo.jwt;

import java.util.ArrayList;

import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class UserAuthenticationProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    public Authentication validateToken(String token) {
            Jwts.parser()
                .setSigningKey(secretKey)  
                .parseClaimsJws(token);    

            String username = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

            Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return authentication;
    }
}
