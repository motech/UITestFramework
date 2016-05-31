package org.motechproject.uitest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * A class that represents Cpmmcare page. Has methods which check functionality of
 * Settings, Import Forms, Forms and Cases.
 */
public class CommcarePage extends MotechPage {

    private static final String HOME_PATH = "/module/server/home#";
    public static final By ADD_CONFIGURATION_BUTTON = By.xpath("(//a[@ng-click='addConfig()'])");
    public static final By SAVE_CONFIGURATION_BUTTON = By.xpath("(//a[@ng-click='saveAllowed() && saveConfig()'])");

    public CommcarePage(WebDriver driver) {
        super(driver);
    }

    public void createNewConfiguration() throws InterruptedException {
        clickWhenVisible(ADD_CONFIGURATION_BUTTON);
    }

    public boolean checkIfAddConfigurationButtonIsVisible() {
        return findElement(ADD_CONFIGURATION_BUTTON).isEnabled();
    }

    public boolean checkIfSaveButtonIsDisabled() {
        boolean check = false;
        try {
            if (findElement(SAVE_CONFIGURATION_BUTTON).getAttribute("disabled").equals("disabled")) {
                check = false;
            }
        } catch (Exception e) {
            check = true;
        }
        return check;
    }

    @Override
    public String expectedUrlPath() {
        return HOME_PATH;
    }
}
