package com.pragma.payments.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import net.serenitybdd.core.Serenity;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Utilidades para generación y validación de tokens JWT en pruebas de seguridad.
 * Proporciona métodos para crear tokens de prueba y validar su integridad.
 */
@Component
public class JwtUtils {

    private static final String DEFAULT_SECRET = "test-secret-key-for-security-tests-minimum-256-bits-required";
    private static final long EXPIRATION_TIME = 3600000;
    private final SecretKey key;

    public JwtUtils() {
        this.key = Keys.hmacShaKeyFor(DEFAULT_SECRET.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Genera un token JWT básico para pruebas.
     */
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", username);
        claims.put("role", "USER");
        return createToken(claims, username);
    }

    /**
     * Genera un token JWT con roles específicos.
     */
    public String generateTokenWithRoles(String username, String... roles) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", username);
        claims.put("roles", roles);
        return createToken(claims, username);
    }

    /**
     * Genera un token JWT con permisos de administrador.
     */
    public String generateAdminToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", username);
        claims.put("role", "ADMIN");
        claims.put("permissions", new String[]{"READ", "WRITE", "DELETE"});
        return createToken(claims, username);
    }

    /**
     * Crea el token JWT con los claims proporcionados.
     */
    private String createToken(Map<String, Object> claims, String subject) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Valida un token JWT y retorna los claims.
     */
    public Claims validateToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Extrae el nombre de usuario del token.
     */
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    /**
     * Extrae la fecha de expiración del token.
     */
    public Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }

    /**
     * Verifica si el token ha expirado.
     */
    public boolean isTokenExpired(String token) {
        try {
            return extractExpiration(token).before(new Date());
        } catch (Exception e) {
            return true;
        }
    }

    /**
     * Valida el token y retorna true si es válido.
     */
    public boolean validateToken(String token, String username) {
        try {
            final String extractedUsername = extractUsername(token);
            return (extractedUsername.equals(username) && !isTokenExpired(token));
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Extrae todos los claims del token.
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Genera un token inválido para pruebas de seguridad.
     */
    public String generateInvalidToken() {
        return "invalid.token.here";
    }

    /**
     * Genera un token expirado para pruebas.
     */
    public String generateExpiredToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", username);
        Date now = new Date();
        Date pastDate = new Date(now.getTime() - 10000);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(pastDate)
                .setExpiration(pastDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Almacena el token en el contexto de Serenity para acceso global.
     */
    public void setAuthToken(String token) {
        Serenity.setSessionVariable("auth_token").to(token);
    }

    /**
     * Recupera el token del contexto de Serenity.
     */
    public String getAuthToken() {
        return Serenity.sessionVariableCalled("auth_token");
    }

    /**
     * Extrae un claim específico del token.
     */
    public Object extractClaim(String token, String claimKey) {
        return extractAllClaims(token).get(claimKey);
    }

    /**
     * Genera un token con claim personalizado.
     */
    public String generateTokenWithCustomClaim(String username, String claimKey, Object claimValue) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", username);
        claims.put(claimKey, claimValue);
        return createToken(claims, username);
    }
}