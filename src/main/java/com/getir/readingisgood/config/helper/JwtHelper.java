package com.getir.readingisgood.config.helper;

import com.getir.readingisgood.dto.TokenDto;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtHelper {

    private final Environment environment;
    
    public TokenDto generateJwtToken(String subject) {
        Date now = Calendar.getInstance().getTime();
        Date expireAt = new Date(now.getTime() + jwtTtl());
        return new TokenDto(Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expireAt)
                .signWith(SignatureAlgorithm.HS512, jwtSecret())
                .compact());
    }

    public String getSubjectOfJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret()).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validate(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret()).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
        } catch (MalformedJwtException e) {
        } catch (ExpiredJwtException e) {
        } catch (UnsupportedJwtException e) {
        } catch (IllegalArgumentException e) {
        }
        return false;
    }

    private Integer jwtTtl() {
        return Integer.parseInt(environment.getProperty("app.jwtExpirationMs"));
    }

    private String jwtSecret() {
        return environment.getProperty("app.jwtSecret");
    }
}
