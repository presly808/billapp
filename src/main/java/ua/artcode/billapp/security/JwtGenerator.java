package ua.artcode.billapp.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ua.artcode.billapp.model.JwtUser;

@Component
public class JwtGenerator {

    @Value("${billapp.token.secret}")
    private String secret;

    public String generate(JwtUser jwtUser) {
        // TODO: setExpTime
        Claims claims = Jwts.claims().setSubject(jwtUser.getPhone());
        claims.put("userId", String.valueOf(jwtUser.getId()));
        claims.put("role", jwtUser.getRole());

        return Jwts.builder().setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
}
