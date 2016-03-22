package org.motechproject.uitest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Objects;

/**
 * A class that represents data services page. Has methods which check functionality of
 * data browser, schema editor and data services settings.
 */

public class DataServicesPage extends AbstractBasePage {

    public static final By ENTITY_NAME_FIELD = By.name("inputEntityName");
    public static final By NEW_ENTITY_BUTTON = By.id("newEntityButton");
    public static final By SAVE_ENTITY_BUTTON = By.id("saveNewEntityButton");
    public static final By DATA_SERVICES_BUTTON = By.id("modulelink_data-services");
    public static final By SCHEMA_EDITOR_BUTTON = By.id("mdsTab_schemaEditor");
    public static final By BROWSE_INSTANCES_BUTTON = By.id("browseInstancesButton");
    public static final By ENTITY_SPAN = By.id("select2-chosen-2");
    public static final By FIELD_TYPE_DROPDOWN = By.id("new-field-type");
    public static final By FIELD_DISPLAY_NAME = By.xpath("//div[@id='new-field-displayName']/input");
    public static final By FIELD_NAME = By.xpath("//div[@id='new-field-name']/input");
    public static final By FIELD_TYPE_BOOLEAN = By.xpath("//div[@class='select2-result-label']/div/div/strong[text() = 'Boolean']");
    public static final By CREATE_FIELD_BUTTON = By.xpath("//a[@ng-click='createField()']");
    public static final By SAVE_CHANGES_BUTTON = By.xpath("//button[@ng-click='saveChanges()']");
    public static final By ENTITIES_DROPDOWN = By.xpath("//div[@id='s2id_selectEntity']/a");
    public static final By BACK_TO_ENTITY_LIST = By.xpath("//button[@ng-click='backToEntityList()']");

    public static final String HOME_PATH = "/module/server/home#";

    public DataServicesPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Method creates new entity in MDS schema editor.
     * @param entityName new entity name
     * @return method returns text that appears in schema editor entity input after creating new entity, should be the same as new entity name, should be checked in tests
     */
    public String createNewEntity(String entityName) throws InterruptedException {
        clickWhenVisible(DATA_SERVICES_BUTTON);
        clickWhenVisible(SCHEMA_EDITOR_BUTTON);
        clickWhenVisible(NEW_ENTITY_BUTTON);
        waitForElement(ENTITY_NAME_FIELD);
        setTextToFieldNoEnter(ENTITY_NAME_FIELD, entityName);
        clickWhenVisible(SAVE_ENTITY_BUTTON);
        waitForElement(BROWSE_INSTANCES_BUTTON);
        return getText(ENTITY_SPAN);
    }

    /**
     * Method that goes to schema editor page and enters entity
     * @param entityName name of the entity we want to edit
     */
    public void goToEditEntity(String entityName) throws InterruptedException {
        clickWhenVisible(DATA_SERVICES_BUTTON);
        clickWhenVisible(SCHEMA_EDITOR_BUTTON);
        clickWhenVisible(ENTITIES_DROPDOWN);
        clickWhenVisible(By.xpath(String.format("//div[@class='select2-result-label']/div/div/strong[text() = '%s']", entityName)));
    }

    /**
     * Method adds new Boolean field to the entity
     * @param fieldName name of the field we want to add
     */
    public void addNewBooleanField(String fieldName) throws InterruptedException {
        setTextToFieldNoEnter(FIELD_DISPLAY_NAME, fieldName);
        setTextToFieldNoEnter(FIELD_NAME, fieldName);
        clickWhenVisible(FIELD_TYPE_DROPDOWN);
        clickWhenVisible(FIELD_TYPE_BOOLEAN);
        clickWhenVisible(CREATE_FIELD_BUTTON);
        waitForElementToBeEnabled(SAVE_CHANGES_BUTTON);
        clickWhenVisible(SAVE_CHANGES_BUTTON);
        waitForElementToBeDisabled(SAVE_CHANGES_BUTTON);
    }

    /**
     * Method checks if field with given name exist in table
     * @param fieldName name of the field
     * @return returns true if that field exists or false otherwise
     */
    public boolean checkFieldExists(String fieldName) throws InterruptedException {
        return Objects.nonNull(findElement(By.xpath(String.format("//th[@title='%s']", fieldName))));
    }

    /**
     * Method that goes to data services page and enters entity table.
     * @param entityName name of entity table that we want to enter
     */
    public void goToEntityTable(String entityName) throws InterruptedException {
        clickWhenVisible(DATA_SERVICES_BUTTON);
        clickWhenVisible(By.xpath(String.format("//a[@id='entity_%s']/div", entityName)));
        waitForElement(BACK_TO_ENTITY_LIST);
    }

    @Override
    public String expectedUrlPath() {
        return HOME_PATH;
    }

    @Override
    public void goToPage() {
        getDriver().get(getMotechUrl() + HOME_PATH);
    }
}
