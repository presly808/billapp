package ua.artcode.billapp.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import ua.artcode.billapp.model.BaseAccount;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenUtils.class);

    @Value("${billapp.token.secret}")
    private String secret;

    private final long TOKEN_EXP = 604800;

    public String generateToken(BaseAccount baseAccount) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", baseAccount.getPhone());
        claims.put("created", generateCurrentDate());

        return generateToken(claims);
    }


    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + TOKEN_EXP * 1000);
    }

    private Date generateCurrentDate() {
        return new Date(System.currentTimeMillis());
    }

    public String getPhoneFromToken(String token) {
        String phone;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            phone = claims.getSubject();
        } catch (Exception e) {
            phone = null;
        }
        return phone;
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret.getBytes("UTF-8"))
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            claims.put("created", this.generateCurrentDate());
            refreshedToken = this.generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    private String generateToken(Map<String, Object> claims) {
        try {
            return Jwts.builder()
                    .setClaims(claims)
                    .setExpiration(this.generateExpirationDate())
                    .signWith(SignatureAlgorithm.HS512, secret.getBytes("UTF-8"))
                    .compact();
        } catch (UnsupportedEncodingException ex) {
            //didn't want to have this method throw the exception, would rather log it and sign the token like it was before
            LOGGER.warn(ex.getMessage());
            return Jwts.builder()
                    .setClaims(claims)
                    .setExpiration(this.generateExpirationDate())
                    .signWith(SignatureAlgorithm.HS512, secret)
                    .compact();
        }
    }

    public boolean isTokenValid(long expDate){
        return expDate > System.currentTimeMillis();
    }
}
