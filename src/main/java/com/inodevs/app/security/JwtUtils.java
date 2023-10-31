package com.inodevs.app.security;

import java.util.Date;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.IOException;
import io.jsonwebtoken.security.Keys;

public class JwtUtils {

    private static final String KEY = "com.inodevs.gerenciamentovagas.backend";

    public static String generateToken(Authentication empresa) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Login empresaSemSenha = new Login();
        empresaSemSenha.setUsername(empresa.getName());
        if (!empresa.getAuthorities().isEmpty()) {
            empresaSemSenha.setAuth(empresa.getAuthorities().iterator().next().getAuthority());
        }
        String empresaJson = mapper.writeValueAsString(empresaSemSenha);
        Date agora = new Date();
        Long hora = 1000L * 60L * 60L; // Uma hora
        return Jwts.builder()
            .claim("userDetails", empresaJson)
            .setIssuer("com.inodevs")
            .setSubject(empresa.getName())
            .setExpiration(new Date(agora.getTime() + hora))
            .signWith(Keys.hmacShaKeyFor(KEY.getBytes()), SignatureAlgorithm.HS256).compact();  
    }
    
    public static Authentication parseToken(String token) 
            throws IOException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String credentialsJson = Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor(KEY.getBytes())).build()
            .parseClaimsJws(token).getBody().get("userDetails", String.class);
        Login empresa = mapper.readValue(credentialsJson, Login.class);
        UserDetails userDetails = User.builder().username(empresa.getUsername()).password("secret")
            .authorities(empresa.getAuth()).build();    
        return new UsernamePasswordAuthenticationToken(empresa.getUsername(), empresa.getPassword(),
            userDetails.getAuthorities());
    }
}