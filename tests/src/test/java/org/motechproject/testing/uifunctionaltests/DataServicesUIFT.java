package org.motechproject.testing.uifunctionaltests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.motechproject.uitest.TestBase;
import org.motechproject.uitest.page.DataServicesPage;

import static org.junit.Assert.assertEquals;


public class DataServicesUIFT extends TestBase {

    public static final String ENTITY_NAME = "newEntity";

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
        dataServicesPage.createNewEntity(ENTITY_NAME);

        assertEquals(ENTITY_NAME, dataServicesPage.getChosenEntityName());
        dataServicesPage.goToPage();
        dataServicesPage.goToEntityTable(ENTITY_NAME);
    }
}