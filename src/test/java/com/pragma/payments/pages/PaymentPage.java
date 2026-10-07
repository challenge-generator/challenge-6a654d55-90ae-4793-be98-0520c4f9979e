package com.pragma.payments.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;
import java.util.List;

public class PaymentPage extends PageObject {

    @FindBy(how = How.ID, using = "payment-form")
    private WebElementFacade paymentForm;

    @FindBy(how = How.ID, using = "amount")
    private WebElementFacade amountField;

    @FindBy(how = How.ID, using = "card-number")
    private WebElementFacade cardNumberField;

    @FindBy(how = How.ID, using = "card-holder")
    private WebElementFacade cardHolderField;

    @FindBy(how = How.ID, using = "expiry-date")
    private WebElementFacade expiryDateField;

    @FindBy(how = How.ID, using = "cvv")
    private WebElementFacade cvvField;

    @FindBy(how = How.ID, using = "recipient-account")
    private WebElementFacade recipientAccountField;

    @FindBy(how = How.ID, using = "recipient-bank")
    private WebElementFacade recipientBankField;

    @FindBy(how = How.ID, using = "reference-number")
    private WebElementFacade referenceNumberField;

    @FindBy(how = How.ID, using = "submit-payment")
    private WebElementFacade submitButton;

    @FindBy(how = How.ID, using = "payment-success-message")
    private WebElementFacade successMessage;

    @FindBy(how = How.ID, using = "payment-error-message")
    private WebElementFacade errorMessage;

    @FindBy(how = How.CSS, using = ".error-message")
    private List<WebElementFacade> validationErrors;

    @FindBy(how = How.ID, using = "login-username")
    private WebElementFacade loginUsernameField;

    @FindBy(how = How.ID, using = "login-password")
    private WebElementFacade loginPasswordField;

    @FindBy(how = How.ID, using = "login-submit")
    private WebElementFacade loginSubmitButton;

    @FindBy(how = How.ID, using = "user-dashboard")
    private WebElementFacade userDashboard;

    @FindBy(how = How.CSS, using = ".transaction-row")
    private List<WebElementFacade> transactionRows;

    @FindBy(how = How.ID, using = "search-transactions")
    private WebElementFacade searchField;

    @FindBy(how = How.ID, using = "filter-transactions")
    private WebElementFacade filterDropdown;

    private static final By SQL_INJECTION_VULNERABLE_FIELD = By.id("search-transactions");
    private static final By SQL_INJECTION_VULNERABLE_PARAM = By.name("query");
    private static final By DYNAMIC_CONTENT_AREA = By.cssSelector(".transaction-data");
    private static final By ERROR_CONTAINER = By.cssSelector(".alert-danger");

    public void openPaymentPage() {
        open();
        waitForFormToLoad();
    }

    public void waitForFormToLoad() {
        withTimeoutOf(Duration.ofSeconds(10)).waitFor(paymentForm);
    }

    public void enterAmount(String amount) {
        amountField.type(amount);
    }

    public void enterCardNumber(String cardNumber) {
        cardNumberField.type(cardNumber);
    }

    public void enterCardHolder(String holderName) {
        cardHolderField.type(holderName);
    }

    public void enterExpiryDate(String expiryDate) {
        expiryDateField.type(expiryDate);
    }

    public void enterCvv(String cvv) {
        cvvField.type(cvv);
    }

    public void enterRecipientAccount(String accountNumber) {
        recipientAccountField.type(accountNumber);
    }

    public void enterRecipientBank(String bankCode) {
        recipientBankField.type(bankCode);
    }

    public void enterReferenceNumber(String reference) {
        referenceNumberField.type(reference);
    }

    public void submitPayment() {
        submitButton.click();
        waitForPaymentProcessing();
    }

    public void waitForPaymentProcessing() {
        withTimeoutOf(Duration.ofSeconds(15)).waitForJavaScriptExecution(
            "return document.readyState === 'complete'"
        );
    }

    public boolean isPaymentSuccessful() {
        return successMessage.isVisible();
    }

    public boolean isPaymentErrorDisplayed() {
        return errorMessage.isVisible();
    }

    public String getErrorMessageText() {
        return errorMessage.getText();
    }

    public List<String> getValidationErrors() {
        return validationErrors.stream()
            .map(WebElementFacade::getText)
            .toList();
    }

    public void loginAsUser(String username, String password) {
        loginUsernameField.type(username);
        loginPasswordField.type(password);
        loginSubmitButton.click();
        withTimeoutOf(Duration.ofSeconds(10)).waitFor(userDashboard);
    }

    public boolean isUserDashboardVisible() {
        return userDashboard.isVisible();
    }

    public int getTransactionCount() {
        return transactionRows.size();
    }

    public void searchTransaction(String searchTerm) {
        searchField.type(searchTerm);
        searchField.pressEnter();
        withTimeoutOf(Duration.ofSeconds(5)).waitForAjaxCompleted();
    }

    public void filterTransactionsByStatus(String status) {
        filterDropdown.selectByVisibleText(status);
        withTimeoutOf(Duration.ofSeconds(5)).waitForAjaxCompleted();
    }

    public void performSqlInjectionTest(String payload) {
        WebElementFacade vulnerableField = find(SQL_INJECTION_VULNERABLE_FIELD);
        vulnerableField.clear();
        vulnerableField.type(payload);
        vulnerableField.pressEnter();
        withTimeoutOf(Duration.ofSeconds(5)).waitForAjaxCompleted();
    }

    public void performSqlInjectionOnParameter(String parameterName, String payload) {
        String urlWithInjection = String.format(
            "?%s=%s",
            parameterName,
            payload
        );
        openAt(urlWithInjection);
        withTimeoutOf(Duration.ofSeconds(5)).waitForPageToLoad();
    }

    public boolean isErrorMessageDisplayed() {
        return find(ERROR_CONTAINER).isVisible();
    }

    public String getPageContent() {
        return evaluateJavascript("return document.body.innerText");
    }

    public boolean containsDatabaseErrorPattern() {
        String content = getPageContent().toLowerCase();
        return content.contains("sql") || 
               content.contains("database") || 
               content.contains("syntax") ||
               content.contains("ora-") ||
               content.contains("mysql") ||
               content.contains("postgresql") ||
               content.contains("sqlite");
    }

    public boolean containsUnexpectedData(String unexpectedData) {
        return getPageContent().contains(unexpectedData);
    }

    public void clearSearchField() {
        searchField.clear();
    }

    public String getCurrentUrl() {
        return getCurrentUrl();
    }

    public boolean hasPagination() {
        return find(By.cssSelector(".pagination")).isPresent();
    }

    public void navigateToPage(int pageNumber) {
        find(By.cssSelector(".page-link[data-page='" + pageNumber + "']")).click();
        withTimeoutOf(Duration.ofSeconds(5)).waitForAjaxCompleted();
    }
}