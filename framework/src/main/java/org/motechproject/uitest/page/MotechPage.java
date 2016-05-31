package org.motechproject.uitest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * A page in the MOTECH UI - with menu on the left and the top header.
 */
public class MotechPage extends AbstractBasePage {

    public static final By DATA_SERVICES_MENU_LINK = By.id("module-nav-mds-dataBrowser");
    public static final By REST_API_MENU_LINK = By.linkText("REST API");
    public static final By DIALOG_DIV = By.className("bootstrap-dialog");
    public static final By USER_INTERFACE_DROPDOWN = By.id("profile-user-name");
    public static final By LOGOUT_BUTTON = By.id("profile-user-logout");

    public MotechPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public String expectedUrlPath() {
        return "/module/server/";
    }

    public DataServicesPage goToDataServices() throws InterruptedException {
        clickWhenVisible(DATA_SERVICES_MENU_LINK);
        return new DataServicesPage(getDriver());
    }

    public RestApiPage goToRestApi() throws InterruptedException {
        clickWhenVisible(REST_API_MENU_LINK);
        return new RestApiPage(getDriver());
    }

    public boolean checkIfDataServicesModuleIsVisible() {
        return findElement(DATA_SERVICES_MENU_LINK).isDisplayed();
    }

    public LoginPage logOut() throws InterruptedException {
        waitForElementToBeGone(DIALOG_DIV);
        waitForElement(USER_INTERFACE_DROPDOWN);
        clickWhenVisible(USER_INTERFACE_DROPDOWN);
        clickWhenVisible(LOGOUT_BUTTON);
        return new LoginPage(getDriver());
    }

    public void waitUntilDialogIsGone() {
        waitForElementToBeGone(DIALOG_DIV);
    }
}
