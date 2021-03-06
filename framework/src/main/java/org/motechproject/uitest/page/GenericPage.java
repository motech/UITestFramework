package org.motechproject.uitest.page;

import org.openqa.selenium.WebDriver;


/**
 * A Page that represents any page, i.e. a page that we don't (yet) know which page it is.
 */
public final class GenericPage extends AbstractBasePage {

    public GenericPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public String expectedUrlPath() {
        return getDriver().getCurrentUrl();
    }
}
