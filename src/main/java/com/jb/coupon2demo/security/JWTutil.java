package com.jb.coupon2demo.security;

import com.jb.coupon2demo.beans.ClientType;
import com.jb.coupon2demo.beans.UserDetails;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class JWTutil {

        //type of encryption
        private String signatureAlgorithm = SignatureAlgorithm.HS256.getJcaName();
        //secret key
        private String secretKey = "this+is+geri+niv+guy+yoav+best+coupon+project+evevrrrrr";
        //private key
        private Key decodedSecretKey = new SecretKeySpec
                (Base64.getDecoder().decode(secretKey),this.signatureAlgorithm);

        //generate key
        public String generateToken(UserDetails userDetails){
            Map<String,Object> claims = new HashMap<>();
            claims.put("userEmail",userDetails.getEmail());
            //claims.put("userPassword",credentials.getUserPass());  -> you no good for me, i don't need to no body....
            return "Bearer "+createToken(claims,userDetails.getClientType().toString());
        }

        private String createToken(Map<String, Object> claims, String userName) {
            Instant now = Instant.now();
            return Jwts.builder()
                    .setClaims(claims)
                    .setSubject(userName)
                    .setIssuedAt(Date.from(now))
                    .setExpiration(Date.from(now.plus(30, ChronoUnit.MINUTES)))
                    .signWith(decodedSecretKey)
                    .compact();
        }

        private Claims extractAllClamis(String token) throws ExpiredJwtException, MalformedJwtException  {
            JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(decodedSecretKey).build();
            return jwtParser.parseClaimsJws(token).getBody();
        }

        private String extractSignature(String token) throws ExpiredJwtException, MalformedJwtException  {
            JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(decodedSecretKey).build();
            return jwtParser.parseClaimsJws(token).getSignature();
        }

        public String extractSubject(String token)  {
            return extractAllClamis(token.replace("Bearer ","")).getSubject();
        }

        public String checkUser(String token) throws MalformedJwtException {
            Claims claims = extractAllClamis(token.replace("Bearer ",""));
            UserDetails userDetails = new UserDetails();
            userDetails.setClientType(ClientType.valueOf(claims.getSubject()));
            userDetails.setEmail((String)claims.get("userEmail"));
            return generateToken(userDetails);
        }

    }
