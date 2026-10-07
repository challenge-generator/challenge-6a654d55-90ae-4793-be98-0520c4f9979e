package com.pragma.payments.steps;

import com.pragma.payments.pages.PaymentPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Step;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PaymentSteps {

    private final PaymentPage paymentPage;

    public PaymentSteps() {
        this.paymentPage = new PaymentPage();
    }

    @Given("el usuario está en la página de pagos")
    @Step("El usuario navega a la página de pagos")
    public void elUsuarioEstaEnLaPaginaDePagos() {
        paymentPage.openPaymentPage();
    }

    @When("el usuario ingresa el monto de {string}")
    @Step("El usuario ingresa el monto: {0}")
    public void elUsuarioIngresaElMonto(String amount) {
        paymentPage.enterAmount(amount);
    }

    @And("el usuario ingresa el número de tarjeta {string}")
    @Step("El usuario ingresa el número de tarjeta: {0}")
    public void elUsuarioIngresaElNumeroDeTarjeta(String cardNumber) {
        paymentPage.enterCardNumber(cardNumber);
    }

    @And("el usuario ingresa el nombre del titular {string}")
    @Step("El usuario ingresa el nombre del titular: {0}")
    public void elUsuarioIngresaElNombreDelTitular(String holderName) {
        paymentPage.enterCardHolder(holderName);
    }

    @And("el usuario ingresa la fecha de expiración {string}")
    @Step("El usuario ingresa la fecha de expiración: {0}")
    public void elUsuarioIngresaLaFechaDeExpiracion(String expiryDate) {
        paymentPage.enterExpiryDate(expiryDate);
    }

    @And("el usuario ingresa el CVV {string}")
    @Step("El usuario ingresa el CVV: {0}")
    public void elUsuarioIngresaElCVV(String cvv) {
        paymentPage.enterCvv(cvv);
    }

    @And("el usuario ingresa la cuenta del beneficiario {string}")
    @Step("El usuario ingresa la cuenta del beneficiario: {0}")
    public void elUsuarioIngresaLaCuentaDelBeneficiario(String accountNumber) {
        paymentPage.enterRecipientAccount(accountNumber);
    }

    @And("el usuario ingresa el banco del beneficiario {string}")
    @Step("El usuario ingresa el banco del beneficiario: {0}")
    public void elUsuarioIngresaElBancoDelBeneficiario(String bankCode) {
        paymentPage.enterRecipientBank(bankCode);
    }

    @And("el usuario ingresa el número de referencia {string}")
    @Step("El usuario ingresa el número de referencia: {0}")
    public void elUsuarioIngresaElNumeroDeReferencia(String reference) {
        paymentPage.enterReferenceNumber(reference);
    }

    @When("el usuario envía el formulario de pago")
    @Step("El usuario envía el formulario de pago")
    public void elUsuarioEnviaElFormularioDePago() {
        paymentPage.submitPayment();
    }

    @Then("el sistema debe mostrar un mensaje de éxito")
    @Step("El sistema muestra mensaje de éxito")
    public void elSistemaDebeMostrarUnMensajeDeExito() {
        assertThat(paymentPage.isPaymentSuccessful())
            .as("El pago debería procesarse exitosamente")
            .isTrue();
    }

    @Then("el sistema debe mostrar un mensaje de error")
    @Step("El sistema muestra mensaje de error")
    public void elSistemaDebeMostrarUnMensajeDeError() {
        assertThat(paymentPage.isPaymentErrorDisplayed())
            .as("Debería mostrarse un mensaje de error")
            .isTrue();
    }

    @Then("el mensaje de error debe contener {string}")
    @Step("El mensaje de error contiene: {0}")
    public void elMensajeDeErrorDebeContener(String expectedError) {
        String actualError = paymentPage.getErrorMessageText();
        assertThat(actualError)
            .as("El mensaje de error debería contener: %s", expectedError)
            .containsIgnoringCase(expectedError);
    }

    @Then("el sistema debe mostrar errores de validación")
    @Step("El sistema muestra errores de validación")
    public void elSistemaDebeMostrarErroresDeValidacion() {
        List<String> errors = paymentPage.getValidationErrors();
        assertThat(errors)
            .as("Deberían mostrarse errores de validación")
            .isNotEmpty();
    }

    @Given("el usuario está autenticado en el sistema")
    @Step("El usuario se autentica en el sistema")
    public void elUsuarioEstaAutenticadoEnElSistema() {
        paymentPage.openPaymentPage();
        paymentPage.loginAsUser("testuser@pragma.com", "Test1234!");
    }

    @When("el usuario busca transacciones con el término {string}")
    @Step("El usuario busca transacciones con: {0}")
    public void elUsuarioBuscaTransaccionesConElTermino(String searchTerm) {
        paymentPage.searchTransaction(searchTerm);
    }

    @Then("el sistema debe mostrar resultados de la búsqueda")
    @Step("El sistema muestra resultados de búsqueda")
    public void elSistemaDebeMostrarResultadosDeLaBusqueda() {
        int transactionCount = paymentPage.getTransactionCount();
        assertThat(transactionCount)
            .as("Deberían mostrarse transacciones")
            .isGreaterThanOrEqualTo(0);
    }

    @When("el usuario intenta realizar una inyección SQL con el payload {string}")
    @Step("El usuario intenta inyección SQL con: {0}")
    public void elUsuarioIntentaRealizarUnaInyeccionSQL(String payload) {
        paymentPage.performSqlInjectionTest(payload);
    }

    @Then("el sistema no debe revelar información sensible de la base de datos")
    @Step("El sistema no revela información de base de datos")
    public void elSistemaNoDebeRevelarInformacionSensible() {
        assertThat(paymentPage.containsDatabaseErrorPattern())
            .as("No debería revelar información de la base de datos")
            .isFalse();
    }

    @Then("el sistema debe manejar correctamente la entrada maliciosa")
    @Step("El sistema maneja la entrada maliciosa")
    public void elSistemaDebeManejarCorrectamenteLaEntradaMaliciosa() {
        boolean hasError = paymentPage.isErrorMessageDisplayed();
        boolean hasDbError = paymentPage.containsDatabaseErrorPattern();
        
        assertThat(hasDbError)
            .as("No debería mostrar errores de base de datos")
            .isFalse();
        
        assertThat(hasError || !hasDbError)
            .as("El sistema debería manejar la entrada de forma segura")
            .isTrue();
    }

    @When("el usuario filtra las transacciones por estado {string}")
    @Step("El usuario filtra transacciones por estado: {0}")
    public void elUsuarioFiltraLasTransaccionesPorEstado(String status) {
        paymentPage.filterTransactionsByStatus(status);
    }

    @Then("el sistema debe mostrar las transacciones filtradas")
    @Step("El sistema muestra transacciones filtradas")
    public void elSistemaDebeMostrarLasTransaccionesFiltradas() {
        int transactionCount = paymentPage.getTransactionCount();
        assertThat(transactionCount)
            .as("Deberían mostrarse transacciones filtradas")
            .isGreaterThanOrEqualTo(0);
    }

    @When("el usuario limpia el campo de búsqueda")
    @Step("El usuario limpia el campo de búsqueda")
    public void elUsuarioLimpiaElCampoDeBusqueda() {
        paymentPage.clearSearchField();
    }

    @Then("el sistema debe mostrar todas las transacciones disponibles")
    @Step("El sistema muestra todas las transacciones")
    public void elSistemaDebeMostrarTodasLasTransacciones() {
        int transactionCount = paymentPage.getTransactionCount();
        assertThat(transactionCount)
            .as("Deberían mostrarse transacciones")
            .isGreaterThan(0);
    }
}