package org.motechproject.uitest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * A page in the MOTECH UI - with menu on the left and the top header.
 */
public class MotechPage extends AbstractBasePage {

    public MotechPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public String expectedUrlPath() {
        return "/module/server/";
    }

    public LoginPage logOut() throws InterruptedException {
        waitForElement(By.cssSelector("span.ng-binding"));
        clickWhenVisible(By.cssSelector("span.ng-binding"));
        clickOn(By.xpath("//a[@href='j_spring_security_logout']"));
        return new LoginPage(getDriver());
    }
}
