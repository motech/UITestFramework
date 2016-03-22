package org.motechproject.testing.uifunctionaltests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.motechproject.uitest.TestBase;
import org.motechproject.uitest.page.DataServicesPage;


public class DataServicesUIFT extends TestBase {

    public static final String ENTITY_NAME = "newEntity";
    public static final String EMAIL_RECORD_ENTITY = "EmailRecord";
    public static final String NEW_FIELD_NAME = "fieldName";

    private DataServicesPage dataServicesPage;

    @Before
    public void initialize() {
        dataServicesPage = new DataServicesPage(getDriver());
        login();
    }

    @After
    public void cleanUp() throws InterruptedException {
        logout();
    }

    @Test
    public void newEntityTest () throws Exception {
        dataServicesPage.goToPage();
        assertEquals(ENTITY_NAME, dataServicesPage.createNewEntity(ENTITY_NAME));
        dataServicesPage.goToPage();
        dataServicesPage.goToEntityTable(ENTITY_NAME);
    }

    @Test
    public void editEntityTest() throws InterruptedException {
        dataServicesPage.goToEditEntity(EMAIL_RECORD_ENTITY);
        dataServicesPage.addNewBooleanField(NEW_FIELD_NAME);
        dataServicesPage.goToEntityTable(EMAIL_RECORD_ENTITY);
        assertTrue(dataServicesPage.checkFieldExists(NEW_FIELD_NAME));
    }
}