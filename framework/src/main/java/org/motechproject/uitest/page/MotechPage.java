package org.motechproject.uitest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import static java.lang.Thread.sleep;

/**
 * A page in the MOTECH UI - with menu on the left and the top header.
 */
public class MotechPage extends AbstractBasePage {

    /**public static final By DATA_SERVICES_MENU_LINK = By.id("module-nav-mds-dataBrowser");*/
    public static final By DIALOG_DIV = By.className("bootstrap-dialog");
    /**TOP MENU*/
    public static final By REST_API_MENU_LINK = By.id("section-nav-server-rest-documentation");
    public static final By ADMIN_MENU_LINK = By.id("section-nav-admin-module");
    public static final By MODULES_MENU_LINK = By.id("section-nav-server-modules");
    /**Left side MENU*/
    public static final By DATA_SERVICES_MENU_LINK = By.id("modulelink_data-services");
    public static final By COMMCARE_MENU_LINK = By.id("module-nav-commcare-settings");
    public static final By MANAGE_MODULES_MENU_LINK = By.id("modulelink_admin");
    /**Inside modules*/
    public static final By BLOCK_UI_DIV = By.className("blockUI");
    public static final By SELECT_MODULE_DROPDOWN = By.name("moduleId");
    public static final By INSTALL_OR_UPDATE_BUTTON = By.xpath("//*[@id=\"bundleUploadForm\"]/div/div/div[6]/span");
    /**Other*/
    public static final int SLEEP_DURING_INSTALL = 150000;

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

    public CommcarePage goToCommcare() throws InterruptedException {
        clickWhenVisible(COMMCARE_MENU_LINK);
        return new CommcarePage(getDriver());
    }

    public LoginPage logOut() throws InterruptedException {
        waitForElementToBeGone(DIALOG_DIV);
        getLogger().debug("Logging Out");
        waitForElementToBeGone(BLOCK_UI_DIV);
        waitForElement(By.cssSelector("span.ng-binding"));
        clickWhenVisible(By.cssSelector("span.ng-binding"));
        clickOn(By.xpath("//a[@href='j_spring_security_logout']"));
        return new LoginPage(getDriver());
    }

    public void waitUntilDialogIsGone() {
        waitForElementToBeGone(DIALOG_DIV);
    }
    /**
     * Methods to install modules of MOTECH application
    **/
    public MotechPage installModule(String moduleName) throws InterruptedException {
        clickWhenVisible(ADMIN_MENU_LINK);
        clickWhenVisible(MANAGE_MODULES_MENU_LINK);
        Select dropdown = new Select(findElement(SELECT_MODULE_DROPDOWN));
        dropdown.selectByVisibleText(moduleName);
        clickWhenVisible(INSTALL_OR_UPDATE_BUTTON);
        getLogger().debug("Installing module: " + moduleName);
        sleep(SLEEP_DURING_INSTALL);
        String newModuleUrl = "http://localhost:8080/motech-platform-server/module/server/#/" + moduleName;
        getDriver().navigate().to(newModuleUrl);
        return new MotechPage(getDriver());
    }

}
