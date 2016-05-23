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
    private static final String MDS_REST_API_TITLE= "MDS REST API";

    @Before
    public void initialize() throws InterruptedException {
        MotechPage home = login();
        restApiPage = home.goToRestApi();
    }

    @After
    public void cleanUp() throws InterruptedException {
        logout();
    }
    //MOTECH 2168
    @Test
    public void shouldOpenRestApiDocumentation() throws InterruptedException {
        restApiPage.openDataServicsApi();
        assertEquals(MDS_REST_API_TITLE, restApiPage.getApiDocumentationTitle());
        assertEquals(DOCUMENTATION_URL, restApiPage.getDocumentationLink());
    }

}