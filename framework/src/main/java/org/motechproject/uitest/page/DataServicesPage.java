package org.motechproject.uitest.page;

import org.motechproject.uitest.exception.UITestFrameworkException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

/**
 * A class that represents data services page. Has methods which check functionality of
 * data browser, schema editor and data services settings.
 */

public class DataServicesPage extends AbstractBasePage {

    private static final By ENTITY_NAME_FIELD = By.name("inputEntityName");
    private static final By NEW_ENTITY_BUTTON = By.id("newEntityButton");
    private static final By SAVE_ENTITY_BUTTON = By.id("saveNewEntityButton");
    private static final By DATA_SERVICES_BUTTON = By.id("modulelink_data-services");
    private static final By SCHEMA_EDITOR_BUTTON = By.id("mdsTab_schemaEditor");
    private static final By BROWSE_INSTANCES_BUTTON = By.id("browseInstancesButton");
    private static final By ENTITY_SPAN = By.id("select2-chosen-2");
    private static final By FIELD_TYPE_DROPDOWN = By.id("new-field-type");
    private static final By FIELD_DISPLAY_NAME = By.id("new-field-displayName-input");
    private static final By FIELD_NAME = By.id("new-field-name-input");
    private static final By FIELD_TYPE_BOOLEAN = By.id("field-type-Boolean");
    private static final By CREATE_FIELD_BUTTON = By.id("add-new-field");
    private static final By SAVE_CHANGES_BUTTON = By.id("save-changes-button");
    private static final By BACK_TO_ENTITY_LIST = By.id("back-to-entity-button");

    private static final String HOME_PATH = "/module/server/home#";

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
        clickWhenVisible(By.id(String.format("edit_%s", entityName)));
    }

    /**
     * Method adds new Boolean field to the entity
     * @param fieldName name of the field we want to add
     */
    public void addNewBooleanField(String fielldDisplayName, String fieldName) throws InterruptedException {
        setTextToFieldNoEnter(FIELD_DISPLAY_NAME, fielldDisplayName);
        setTextToFieldNoEnter(FIELD_NAME, fieldName);
        clickWhenVisible(FIELD_TYPE_DROPDOWN);
        clickWhenVisible(FIELD_TYPE_BOOLEAN);
        clickWhenVisible(CREATE_FIELD_BUTTON);
        try {
            waitForElementToBeEnabled(SAVE_CHANGES_BUTTON);
        } catch (Exception e) {
            throw new UITestFrameworkException("Wait too long for enable button", e);
        }
        try {
            clickWhenVisible(SAVE_CHANGES_BUTTON);
        } catch (Exception e) {
            throw new UITestFrameworkException("Cannot click button", e);
        }
        try {
            waitForElementToBeDisabled(SAVE_CHANGES_BUTTON);
        } catch (Exception e) {
            throw new UITestFrameworkException("Wait too long for disable button", e);
        }
    }

    /**
     * Method checks if field with given name exist in table
     * @param fieldName name of the field
     * @return returns true if that field exists or false otherwise
     */
    public boolean checkFieldExists(String fieldName) throws InterruptedException {
        return Objects.nonNull(findElement(By.id(String.format("instancesTable_%s", fieldName))));
    }

    /**
     * Method that goes to data services page and enters entity table.
     * @param entityName name of entity table that we want to enter
     */
    public void goToEntityTable(String entityName) throws InterruptedException {
        clickWhenVisible(DATA_SERVICES_BUTTON);
        waitForElement(By.id(String.format("entity_%s", entityName)));
        clickWhenVisible(By.id(String.format("entity_%s", entityName)));
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
