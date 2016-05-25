package org.motechproject.uitest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * A class that represents REST API page. Has methods which check functionality of API Documentation.
 */
public class RestApiPage extends MotechPage {

    private static final String HOME_PATH = "/module/server/home#";
    public static final By MDS_REST_API_TITLE_PATH = By.xpath("//*[@id=\"api_info\"]/div[1]");
    public static final By DATA_SERVICES_API_LINK = By.id("module-nav-rest-docs-data-services");
    public static final By REST_API_DOCUMENTATION_LINK = By.linkText("REST API Documentation.");

    public RestApiPage(WebDriver driver) {
        super(driver);
    }

    public RestApiPage openDataServicsApi() throws InterruptedException {
        clickWhenVisible(DATA_SERVICES_API_LINK);
        return new RestApiPage(getDriver());
    }

    public String getDocumentationLink() throws InterruptedException {
        return findElement(REST_API_DOCUMENTATION_LINK).getAttribute("href");
    }

    public String getApiDocumentationTitle() throws InterruptedException {
        return findElement(MDS_REST_API_TITLE_PATH).getText();
    }

    @Override
    public String expectedUrlPath() {
        return HOME_PATH;
    }
}
