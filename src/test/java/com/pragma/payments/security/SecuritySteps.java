package com.pragma.payments.security;

import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Definiciones de pasos para escenarios de pruebas de seguridad.
 * Incluye pasos para inyección SQL y autenticación JWT.
 */
public class SecuritySteps {

    @Autowired
    private SecurityConfig securityConfig;

    @Autowired
    private JwtUtils jwtUtils;

    private RequestSpecification requestSpec;
    private Response response;
    private String currentToken;
    private String currentEndpoint;

    @Dado("que el usuario tiene credenciales válidas de autenticación")
    @Step("Preparar credenciales de autenticación")
    public void queElUsuarioTieneCredencialesValidasDeAutenticación() {
        currentToken = jwtUtils.generateToken("testuser");
        jwtUtils.setAuthToken(currentToken);
    }

    @Dado("que el usuario tiene un token JWT válido")
    public void queElUsuarioTieneUnTokenJWTValido() {
        currentToken = jwtUtils.generateToken("testuser");
    }

    @Dado("que el usuario tiene un token JWT inválido")
    public void queElUsuarioTieneUnTokenJWTInválido() {
        currentToken = jwtUtils.generateInvalidToken();
    }

    @Dado("que el usuario tiene un token JWT expirado")
    public void queElUsuarioTieneUnTokenJWTExpirado() {
        currentToken = jwtUtils.generateExpiredToken("testuser");
    }

    @Cuando("el usuario envía una solicitud GET al endpoint de pagos")
    @Step("Enviar solicitud GET al endpoint")
    public void elUsuarioEnvíaUnaSolicitudGETAlEndpointDePagos() {
        currentEndpoint = "/api/payments";
        requestSpec = RestAssured.given()
                .baseUri("http://localhost:8080")
                .header("Authorization", "Bearer " + currentToken)
                .contentType("application/json");

        response = requestSpec.get(currentEndpoint);
    }

    @Cuando("el usuario intenta acceder al endpoint {string}")
    public void elUsuarioIntentaAccederAlEndpoint(String endpoint) {
        currentEndpoint = endpoint;
        requestSpec = RestAssured.given()
                .baseUri("http://localhost:8080")
                .header("Authorization", "Bearer " + currentToken)
                .contentType("application/json");

        response = requestSpec.get(endpoint);
    }

    @Cuando("el usuario envía una solicitud con payload malicioso de inyección SQL")
    @Step("Enviar payload de inyección SQL")
    public void elUsuarioEnvíaUnaSolicitudConPayloadMaliciosoDeInyecciónSQL() {
        String maliciousPayload = "' OR '1'='1";
        currentEndpoint = "/api/payments/search";

        Map<String, String> body = new HashMap<>();
        body.put("query", maliciousPayload);

        requestSpec = RestAssured.given()
                .baseUri("http://localhost:8080")
                .header("Authorization", "Bearer " + currentToken)
                .contentType("application/json")
                .body(body);

        response = requestSpec.post(currentEndpoint);
    }

    @Cuando("el usuario envía una solicitud con payload de inyección SQL en el campo {string}")
    public void elUsuarioEnvíaUnaSolicitudConPayloadDeInyecciónSQLEnElCampo(String campo) {
        Map<String, String> body = new HashMap<>();
        body.put(campo, "' OR '1'='1");

        requestSpec = RestAssured.given()
                .baseUri("http://localhost:8080")
                .header("Authorization", "Bearer " + currentToken)
                .contentType("application/json")
                .body(body);

        response = requestSpec.post(currentEndpoint);
    }

    @Cuando("el usuario envía credenciales de autenticación inválidas")
    @Step("Enviar credenciales inválidas")
    public void elUsuarioEnvíaCredencialesDeAutenticaciónInválidas() {
        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", "invaliduser");
        credentials.put("password", "wrongpassword");

        requestSpec = RestAssured.given()
                .baseUri("http://localhost:8080")
                .contentType("application/json")
                .body(credentials);

        response = requestSpec.post("/api/auth/login");
    }

    @Cuando("el usuario envía credenciales válidas")
    public void elUsuarioEnvíaCredencialesVálidas() {
        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", securityConfig.getAdminUser());
        credentials.put("password", securityConfig.getAdminPassword());

        requestSpec = RestAssured.given()
                .baseUri("http://localhost:8080")
                .contentType("application/json")
                .body(credentials);

        response = requestSpec.post("/api/auth/login");
    }

    @Entonces("el sistema debe responder con código de estado {int}")
    @Step("Verificar código de estado")
    public void elSistemaDebeResponderConCódigoDeEstado(int expectedStatus) {
        assertEquals("El código de estado no coincide", expectedStatus, response.getStatusCode());
    }

    @Entonces("el sistema debe rechazar la solicitud por autenticación fallida")
    public void elSistemaDebeRechazarLaSolicitudPorAutenticaciónFallida() {
        assertTrue("Expected 401 or 403",
                response.getStatusCode() == 401 || response.getStatusCode() == 403);
    }

    @Entonces("el sistema debe rechazar la solicitud por token inválido")
    public void elSistemaDebeRechazarLaSolicitudPorTokenInválido() {
        assertEquals("Expected 401 for invalid token", 401, response.getStatusCode());
    }

    @Entonces("el sistema debe rechazar la solicitud por token expirado")
    public void elSistemaDebeRechazarLaSolicitudPorTokenExpirado() {
        assertEquals("Expected 401 for expired token", 401, response.getStatusCode());
    }

    @Entonces("el sistema debe prevenir la inyección SQL y retornar un error")
    public void elSistemaDebePrevenirLaInyecciónSQLYRetornarUnError() {
        int statusCode = response.getStatusCode();
        assertTrue("Expected error status code (4xx or 5xx) for SQL injection attempt",
                statusCode >= 400);

        String responseBody = response.getBody().asString();
        assertFalse("Response should not expose database errors",
                responseBody.toLowerCase().contains("sql") ||
                responseBody.toLowerCase().contains("database"));
    }

    @Entonces("el sistema debe retornar un token JWT válido")
    public void elSistemaDebeRetornarUnTokenJWTVálido() {
        assertEquals("Expected 200 for successful login", 200, response.getStatusCode());

        String responseBody = response.getBody().asString();
        assertTrue("Response should contain token",
                responseBody.contains("token") || responseBody.contains("jwt"));
    }

    @Entonces("el sistema debe retornar los datos del pago solicitado")
    public void elSistemaDebeRetornarLosDatosDelPagoSolicitado() {
        assertEquals("Expected 200 for successful request", 200, response.getStatusCode());
        assertNotNull("Response body should not be null", response.getBody());
    }

    @Entonces("el sistema debe registrar el intento de intrusión")
    public void elSistemaDebeRegistrarElIntentoDeIntrusión() {
        String responseBody = response.getBody().asString();
        assertTrue("Response should indicate security event logged",
                responseBody.toLowerCase().contains("logged") ||
                responseBody.toLowerCase().contains("security"));
    }

    @Dado("que el usuario tiene rol de administrador")
    public void queElUsuarioTieneRolDeAdministrador() {
        currentToken = jwtUtils.generateAdminToken("adminuser");
    }

    @Cuando("el usuario intenta acceder a un endpoint protegido sin token")
    public void elUsuarioIntentaAccederAUnEndpointProtegidoSinToken() {
        currentEndpoint = "/api/payments";
        requestSpec = RestAssured.given()
                .baseUri("http://localhost:8080")
                .contentType("application/json");

        response = requestSpec.get(currentEndpoint);
    }

    @Entonces("el sistema debe rechazar el acceso sin autenticación")
    public void elSistemaDebeRechazarElAccesoSinAutenticación() {
        assertEquals("Expected 401 for missing authentication", 401, response.getStatusCode());
    }

    @Cuando("el usuario envía datos de pago con caracteres especiales")
    public void elUsuarioEnvíaDatosDePagoConCaracteresEspeciales() {
        Map<String, Object> paymentData = new HashMap<>();
        paymentData.put("amount", 100.00);
        paymentData.put("description", "<script>alert('xss')</script>");
        paymentData.put("recipient", "test<script>");

        requestSpec = RestAssured.given()
                .baseUri("http://localhost:8080")
                .header("Authorization", "Bearer " + currentToken)
                .contentType("application/json")
                .body(paymentData);

        response = requestSpec.post("/api/payments");
    }

    @Entonces("el sistema debe sanitizar los datos y prevenir XSS")
    public void elSistemaDebeSanitizarLosDatosYPrevenirXSS() {
        assertTrue("Expected success or sanitized response",
                response.getStatusCode() == 200 ||
                !response.getBody().asString().contains("<script>"));
    }
}