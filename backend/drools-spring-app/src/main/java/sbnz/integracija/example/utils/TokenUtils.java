package sbnz.integracija.example.utils;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import sbnz.integracija.example.model.enums.Role;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

@Component
public class TokenUtils {

    private static TokenUtils instance;

    private TokenUtils() {

    }

    public static TokenUtils getInstance() {
        if(instance == null)
            instance = new TokenUtils();
        return instance;
    }

    public String SECRET = "somesecret";
    private int EXPIRES_IN = 9000000;
    private String AUTH_HEADER = "Authorization";
    private final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;

    public String generateToken(UUID id, String username, Role role) {
        return Jwts.builder()
                .setSubject(username)
                .setId(id.toString())
                .setIssuer(role.toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + EXPIRES_IN))
                .signWith(SIGNATURE_ALGORITHM, SECRET).compact();
    }

    public Boolean validateToken(String token) {
        try {
            Jwt jwt = Jwts.parser().setSigningKey(SECRET).parse(token);
            if(jwt != null)
                return true;
        } catch (Exception e) {

        }
        return false;
    }

    public String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    public UUID getUserIdFromToken(String token) {
        String userId;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            userId = claims.getId();
        } catch (Exception e) {
            userId = null;
        }
        return UUID.fromString(userId);
    }

    public String getRoleFromToken(String token) {
        String role;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            role = claims.getIssuer();
        } catch (Exception e) {
            role = null;
        }
        return role;
    }

    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    public String getToken(HttpServletRequest request) {
        String authHeader = getAuthHeaderFromHeader(request);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }

        return null;
    }

    public String getAuthHeaderFromHeader(HttpServletRequest request) {
        return request.getHeader(AUTH_HEADER);
    }

    private Claims getAllClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

}