package org.motechproject.testing.uifunctionaltests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.motechproject.uitest.TestBase;
import org.motechproject.uitest.page.DataServicesPage;
import org.motechproject.uitest.page.MotechPage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class DataServicesUIFT extends TestBase {

    private static final String ENTITY_NAME = "newEntity";
    private static final String EMAIL_RECORD_ENTITY = "EmailRecord";
    private static final String NEW_FIELD_NAME = "fieldName";
    private static final String NEW_FIELD_DISPLAY_NAME = "Field Name";

    private DataServicesPage dataServicesPage;

    @Before
    public void initialize() throws InterruptedException {
        MotechPage home = login();
        dataServicesPage = home.goToDataServices();
    }

    @After
    public void cleanUp() throws InterruptedException {
        logout();
    }

    @Test
    public void editEntityTest() throws InterruptedException {
        dataServicesPage.goToEditEntity(EMAIL_RECORD_ENTITY);
        dataServicesPage.addNewBooleanField(NEW_FIELD_DISPLAY_NAME, NEW_FIELD_NAME);
        dataServicesPage.goToEntityTable(EMAIL_RECORD_ENTITY);
        assertTrue(dataServicesPage.checkFieldExists(NEW_FIELD_NAME));
    }
    
    public void newEntityTest () throws Exception {
        dataServicesPage.createNewEntity(ENTITY_NAME);

        assertEquals(ENTITY_NAME, dataServicesPage.getChosenEntityName());
       
        dataServicesPage.goToEntityTable(ENTITY_NAME);
    }
}