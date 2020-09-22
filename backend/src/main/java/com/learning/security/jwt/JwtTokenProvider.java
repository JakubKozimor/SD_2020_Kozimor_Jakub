package com.learning.security.jwt;

import com.learning.security.model.UserPrincipal;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider {

    @Value("${token.jwtSecret}")
    private String jwtSecretKey;
    @Value("${token.jwtExpirationInMs}")
    private int expiate;

    public String generateToken(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        Date now = new Date();
        Date expDate = new Date(now.getTime() + expiate);

        return Jwts.builder()
                .setSubject(Long.toString(userPrincipal.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecretKey)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            log.error("Invalid JWT signature! Message - {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token! Message - {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token! Message - {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT token! Message - {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty! Message - {}", e.getMessage());
        }
        return false;
    }

    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecretKey)
                .parseClaimsJws(token)
                .getBody();
        return Long.parseLong(claims.getSubject());
    }
}
