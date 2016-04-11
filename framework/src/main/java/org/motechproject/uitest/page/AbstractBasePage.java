package org.motechproject.uitest.page;


import com.google.common.base.Predicate;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * A superclass for "real" pages. Has lots of handy methods for accessing
 * elements, clicking, filling fields. etc.
 */
public abstract class AbstractBasePage implements Page {

    public static final int MAX_WAIT_SECONDS = 60;
    public static final int TEN_SECONDS = 10000;
    public static final int HALF_SECOND = 500;

    private WebDriver driver;
    private TestProperties properties = TestProperties.instance();
    private WebDriverWait waiter;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public AbstractBasePage(WebDriver driver) {
        this.driver = driver;
        waiter = new WebDriverWait(driver, MAX_WAIT_SECONDS);
    }

    @Override
    public WebElement findElement(By by) {
        waiter.until(ExpectedConditions.presenceOfElementLocated(by));
        return driver.findElement(by);
    }

    @Override
    public WebElement findElementById(String id) {
        return findElement(By.id(id));
    }

    @Override
    public String getText(By by) {
        return findElement(by).getText();
    }

    @Override
    public void setText(By by, String text) {
        setText(findElement(by), text);
    }

    @Override
    public void setText(String id, String text) {
        setText(findElement(By.id(id)), text);
    }

    @Override
    public void setTextToFieldNoEnter(By by, String text) {
        setTextNoEnter(findElement(by), text);
    }

    @Override
    public void setTextToFieldInsideSpan(String spanId, String text) {
        setText(findTextFieldInsideSpan(spanId), text);
    }

    @Override
    public void clickOn(By by) {
        getLogger().debug("Clicking on: {}", by);
        findElement(by).click();
    }

    @Override
    public void selectFrom(By by, String value) {
        getLogger().debug("Selecting {} from droplist: {}", value, by);
        Select droplist = new Select(findElement(by));
        droplist.selectByVisibleText(value);
    }

    @Override
    public void hoverOn(By by) {
        getLogger().debug("Hovering on: {}", by);
        Actions builder = new Actions(driver);
        Actions hover = builder.moveToElement(findElement(by));
        hover.perform();
    }

    @Override
    public String title() {
        getLogger().debug("Retrieving page title");
        return getText(By.tagName("title"));
    }

    @Override
    public String urlPath() {
        try {
            return new URL(driver.getCurrentUrl()).getPath();
        } catch (MalformedURLException e) {
            return null;
        }
    }

    @Override
    public List<WebElement> findElements(By by) {
        getLogger().debug("Waiting for presence of elements located by: {}", by);
        waiter.until(ExpectedConditions.presenceOfElementLocated(by));
        getLogger().debug("Retrieving elements located by: {}", by);
        return driver.findElements(by);
    }

    @Override
    public void goToPage() {
        String path = getMotechUrl() + expectedUrlPath();
        getLogger().debug("Going to: {}", path);
        getDriver().get(path);
    }

    /**
     * Real pages supply their expected URL path.
     *
     * @return The path portion of the url of the page.
     */
    @Override
    public abstract String expectedUrlPath();

    public void clickOnLinkFromHref(String href) throws InterruptedException {
        // We allow use of xpath here because href's tend to be quite stable.
        clickWhenVisible(byFromHref(href));
    }

    public By byFromHref(String href) {
        return By.xpath("//a[@href='" + href + "']");
    }

    public void waitForFocusById(final String id) {
        getLogger().debug("Waiting for focus on element located by id: {}", id);
        waiter.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return hasFocus(id);
            }
        });
    }

    public void waitForFocusByCss(final String tag, final String attr, final String value) {
        getLogger().debug("Waiting for focus on element located by css - tag: {}, attr: {}, value: {}",
                tag, attr, value);
        waiter.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return hasFocus(tag, attr, value);
            }
        });
    }

    public void waitForJsVariable(final String varName) {
        getLogger().debug("Waiting for JS variable: {}", varName);
        waiter.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return (Boolean) ((JavascriptExecutor) driver).executeScript("return " + varName);
            }
        });
    }

    public void waitForElementToBeHidden(By by) {
        getLogger().debug("Waiting for element to be hidden: {}", by);
        waiter.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public void waitForElementToBeGone(By by) {
        getLogger().debug("Waiting for element to be gone: {}", by);
        waiter.until(new Predicate<WebDriver>() {
            @Override
            public boolean apply(WebDriver input) {
                List<WebElement> elems = input.findElements(by);
                return elems.size() == 0;
            }
        });
    }

    public void waitForElementToBeEnabled(By by) {
        getLogger().debug("Waiting for element to be clickable: {}", by);
        waiter.until(ExpectedConditions.elementToBeClickable(by));
    }

    public void waitForElementToBeDisabled(By by) {
        waiter.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(by)));
    }

    public boolean hasFocus(String id) {
        return (Boolean) ((JavascriptExecutor) driver).executeScript("return jQuery('#" + id +  "').is(':focus')");
    }

    public boolean hasFocus(String tag, String attr, String value) {
        return (Boolean) ((JavascriptExecutor) driver).executeScript("return jQuery('" + tag + "[" + attr + "=" + value + "]').is(':focus')");
    }

    public void waitForElement(By by) {
        getLogger().debug("Waiting for element to be visisble: {}", by);
        waiter.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void waitForTextToBePresentInElement(By by, String text) {
        getLogger().debug("Waiting for text \"{}\" to be present in element: {}", text, by);
        waiter.until(ExpectedConditions.textToBePresentInElementLocated(by, text));
    }

    public Boolean containsText(String text) {
        return driver.getPageSource().contains(text);
    }

    public void clickWhenVisible(By by) throws InterruptedException {
        getLogger().debug("Waiting until element is clickable: {}", by);
        waiter.until(ExpectedConditions.elementToBeClickable(by));
        clickOn(by);
    }

    protected TestProperties getProperties() {
        return properties;
    }

    protected String getServerURL() {
        return properties.getServerUrl();
    }

    protected String getContextPath() {
        return properties.getContextPath();
    }

    protected String getMotechUrl() {
        return getServerURL() + getContextPath();
    }

    protected WebDriver getDriver() {
        return driver;
    }

    protected WebDriverWait getWaiter() {
        return waiter;
    }

    protected Logger getLogger() {
        return logger;
    }

    private void setText(WebElement element, String text) {
        setTextNoEnter(element, text);
        element.sendKeys(Keys.RETURN);
    }

    private void setTextNoEnter(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    private WebElement findTextFieldInsideSpan(String spanId) {
        return findElementById(spanId).findElement(By.tagName("input"));
    }
}
