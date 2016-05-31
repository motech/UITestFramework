package org.motechproject.uitest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends AbstractBasePage {

    private static final By USERNAME = By.name("j_username");
    private static final By PASSWORD = By.name("j_password");
    private static final By LOGIN = By.cssSelector("input.btn.btn-primary");
    private static final By WRONG_LOGIN_OR_PASSWORD_STATEMENT = By.xpath("//*[contains(text(),'Wrong password!')]");

    public static final String LOGIN_PATH = "/module/server/login";

    private final String adminUsername;
    private final String adminPassword;

    public LoginPage(WebDriver driver) {
        super(driver);
        adminUsername = getProperties().getUserName();
        adminPassword = getProperties().getPassword();
    }

    public MotechPage login(String user, String password) {
        waitForElement(USERNAME);
        setTextToFieldNoEnter(USERNAME, user);
        setTextToFieldNoEnter(PASSWORD, password);
        clickOn(LOGIN);
        waitForElement(By.cssSelector("span.ng-binding"));
        return new MotechPage(getDriver());
    }

    public LoginPage loginWithWrongPassword(String user, String password) {
        waitForElement(USERNAME);
        setTextToFieldNoEnter(USERNAME, user);
        setTextToFieldNoEnter(PASSWORD, password);
        clickOn(LOGIN);
        return new LoginPage(getDriver());
    }

    public MotechPage loginAsAdmin() {
        return login(adminUsername, adminPassword);
    }

    public boolean checkIfStatementForWrongPasswordAppears() {
        return findElement(WRONG_LOGIN_OR_PASSWORD_STATEMENT).isDisplayed();
    }

    @Override
    public String expectedUrlPath() {
        return LOGIN_PATH;
    }

    @Override
    public void goToPage() {
        getDriver().get(getMotechUrl() + expectedUrlPath());
    }
}
