package com.pragma.payments.security;

import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Configuración de seguridad para pruebas automatizadas.
 * Proporciona utilitários para manejar autenticación JWT y configuración de seguridad.
 */
@Component
public class SecurityConfig extends PageObject {

    private static final String DEFAULT_SECRET_KEY = "test-secret-key-for-security-tests-minimum-256-bits-required";
    private static final long DEFAULT_EXPIRATION = 3600000;
    private static Map<String, String> tokenStore = new HashMap<>();

    public SecurityConfig() {
        super();
        setDefaultBaseUrl("http://localhost:8080");
    }

    /**
     * Configura el actor de pruebas con credenciales de seguridad.
     */
    public static void configureSecurityActor(String actorName) {
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled(actorName);
    }

    /**
     * Obtiene la clave secreta configurada para JWT.
     */
    public String getSecretKey() {
        return DEFAULT_SECRET_KEY;
    }

    /**
     * Obtiene el tiempo de expiración de tokens.
     */
    public long getTokenExpiration() {
        return DEFAULT_EXPIRATION;
    }

    /**
     * Almacena un token para uso posterior en las pruebas.
     */
    public static void storeToken(String key, String token) {
        tokenStore.put(key, token);
    }

    /**
     * Recupera un token almacenado previamente.
     */
    public static String getStoredToken(String key) {
        return tokenStore.get(key);
    }

    /**
     * Limpia todos los tokens almacenados.
     */
    public static void clearTokens() {
        tokenStore.clear();
    }

    /**
     * Valida si un token tiene el formato correcto.
     */
    public boolean isValidTokenFormat(String token) {
        if (token == null || token.isEmpty()) {
            return false;
        }
        String[] parts = token.split("\\.");
        return parts.length == 3;
    }

    /**
     * Obtiene los headers de autorización para requests.
     */
    public Map<String, String> getAuthHeaders(String token) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + token);
        headers.put("Content-Type", "application/json");
        return headers;
    }

    /**
     * Configura el contexto de seguridad para pruebas de inyección SQL.
     */
    public void configureSqlInjectionTestContext() {
        setDefaultBaseUrl("http://localhost:8080");
    }

    /**
     * Obtiene el usuario admin para pruebas.
     */
    public String getAdminUser() {
        return "admin";
    }

    /**
     * Obtiene la contraseña admin para pruebas.
     */
    public String getAdminPassword() {
        return "admin123";
    }
}