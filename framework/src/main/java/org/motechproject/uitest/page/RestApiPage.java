package org.motechproject.uitest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RestApiPage extends MotechPage {

    private static final String HOME_PATH = "/module/server/home#";
    public static final By DATA_SERVICES = By.id("modulelink_rest-docs");
    public static final By REST_API_DOCUMENTATION_LINK = By.linkText("REST API Documentation.");


    public RestApiPage(WebDriver driver) {
        super(driver);
    }

    public void openDataServics() throws InterruptedException {
         clickWhenVisible(DATA_SERVICES);
    }

    public String getDocumentationLink() throws InterruptedException {
        String href = findElement(REST_API_DOCUMENTATION_LINK).getAttribute("href");
        return href;
    }

    @Override
    public String expectedUrlPath() {
        return HOME_PATH;
    }
}
