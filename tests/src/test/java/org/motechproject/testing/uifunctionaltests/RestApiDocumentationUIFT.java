package org.motechproject.testing.uifunctionaltests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.motechproject.uitest.TestBase;
import org.motechproject.uitest.page.MotechPage;
import org.motechproject.uitest.page.RestApiPage;

import static org.junit.Assert.assertEquals;


public class RestApiDocumentationUIFT extends TestBase {

    private RestApiPage restApiPage;
    private static final String DOCUMENTATION_URL = "http://docs.motechproject.org/en/latest/get_started/model_data/model_data.html#the-rest-api";

    @Before
    public void initialize() throws InterruptedException {
        MotechPage home = login();
        restApiPage = home.goToRestApi();
    }

    @After
    public void cleanUp() throws InterruptedException {
        logout();
    }

    @Test
    public void openRestApiDocumentation() throws InterruptedException {
        restApiPage.openDataServics();
        assertEquals(DOCUMENTATION_URL,restApiPage.getDocumentationLink());
    }

}