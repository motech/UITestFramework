package org.motechproject.uitest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

/**
 * A class that represents data services page. Has methods which check functionality of
 * data browser, schema editor and data services settings.
 */

public class DataServicesPage extends MotechPage {

    public static final By ENTITY_NAME_FIELD = By.name("inputEntityName");
    public static final By NEW_ENTITY_BUTTON = By.id("newEntityButton");
    public static final By SAVE_ENTITY_BUTTON = By.id("saveNewEntityButton");

    public static final By SCHEMA_EDITOR_TAB = By.id("mdsTab_schemaEditor");
    public static final By DATA_BROWSER_TAB = By.id("mdsTab_dataBrowser");
    public static final By BROWSE_INSTANCES_BUTTON = By.id("browseInstancesButton");
    public static final By ADD_NEW_INSTANCE_BUTTON = By.id("addNewInstanceButton");
    public static final By ENTITY_SPAN = By.id("select2-chosen-1");

    public static final By FIELD_TYPE_DROPDOWN = By.id("new-field-type");
    public static final By FIELD_DISPLAY_NAME = By.id("new-field-displayName-input");
    public static final By FIELD_NAME = By.id("new-field-name-input");
    public static final By FIELD_TYPE_BOOLEAN = By.id("field-type-Boolean");
    public static final By CREATE_FIELD_BUTTON = By.id("add-new-field");
    public static final By SAVE_CHANGES_BUTTON = By.id("save-changes-button");
    public static final String FIELD_TYPE = "field-type-";


    private static final String HOME_PATH = "/module/server/home#";

    public DataServicesPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Method creates new entity in MDS schema editor.
     * @param entityName new entity name
     * @return method returns text that appears in schema editor entity input after creating new entity, should be the same as new entity name, should be checked in tests
     */
    public DataServicesPage createNewEntity(String entityName) throws InterruptedException {
        waitUntilDialogIsGone();
        clickWhenVisible(SCHEMA_EDITOR_TAB);
        clickWhenVisible(NEW_ENTITY_BUTTON);
        waitForElement(ENTITY_NAME_FIELD);
        setTextToFieldNoEnter(ENTITY_NAME_FIELD, entityName);
        clickWhenVisible(SAVE_ENTITY_BUTTON);
        waitUntilDialogIsGone();
        waitForElement(BROWSE_INSTANCES_BUTTON);
        return this;
    }

    /**
     * Goes to schema editor page and enters entity
     * @param entityName name of the entity we want to edit
     */
    public DataServicesPage goToEditEntity(String entityName) throws InterruptedException {
        waitUntilDialogIsGone();
        clickWhenVisible(DATA_BROWSER_TAB);
        waitUntilDialogIsGone();
        clickWhenVisible(By.id(String.format("edit_%s", entityName)));
        waitUntilDialogIsGone();
        return this;
    }

    /**
     * Adds new {@code boolean} field to the entity
     * @param fieldDisplayName display name of the field to be added
     * @param fieldName name of the field to be added
     */
    public void addNewBooleanField(String fieldDisplayName, String fieldName) throws InterruptedException {
        setTextToFieldNoEnter(FIELD_DISPLAY_NAME, fieldDisplayName);
        setTextToFieldNoEnter(FIELD_NAME, fieldName);
        clickWhenVisible(FIELD_TYPE_DROPDOWN);
        clickWhenVisible(FIELD_TYPE_BOOLEAN);
        clickWhenVisible(CREATE_FIELD_BUTTON);
        waitUntilDialogIsGone();
        clickWhenVisible(SAVE_CHANGES_BUTTON);
        waitUntilDialogIsGone();
    }

    public void addNewField(String type) throws InterruptedException {
        clickWhenVisible(FIELD_TYPE_DROPDOWN);
        clickWhenVisible(By.id(FIELD_TYPE + type));
        findElement(FIELD_DISPLAY_NAME).sendKeys(type);
        clickWhenVisible(CREATE_FIELD_BUTTON);
        clickWhenVisible(SAVE_CHANGES_BUTTON);
        waitUntilDialogIsGone();
    }

    public boolean checkIfSaveChangesButtonIsDisabled() {
        return findElement(SAVE_CHANGES_BUTTON).isEnabled();
    }

    /**
     * Checks if field with given name exist in table
     * @param fieldName name of the field
     * @return true if that field exists or false otherwise
     */
    public boolean checkFieldExists(String fieldName) throws InterruptedException {
        return Objects.nonNull(findElement(By.id(String.format("instancesTable_%s", fieldName))));
    }

    /**
     * Goes to data services page and enters entity table.
     * @param entityName name of entity table that we want to enter
     */
    public DataServicesPage goToEntityTable(String entityName) throws InterruptedException {
        waitUntilDialogIsGone();
        clickWhenVisible(DATA_BROWSER_TAB);
        waitUntilDialogIsGone();
        clickWhenVisible(By.id(String.format("entity_%s", entityName)));
        waitUntilDialogIsGone();
        return this;
    }

    public String getChosenEntityName() {
        return getText(ENTITY_SPAN);
    }

    @Override
    public String expectedUrlPath() {
        return HOME_PATH;
    }
}
