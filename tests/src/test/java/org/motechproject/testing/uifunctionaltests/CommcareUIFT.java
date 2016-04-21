package org.motechproject.testing.uifunctionaltests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.motechproject.uitest.TestBase;
import org.motechproject.uitest.page.CommcarePage;
import org.motechproject.uitest.page.MotechPage;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;

public class CommcareUIFT extends TestBase {

    private CommcarePage commcarePage;
    private static int SLEEP_500 = 500;

    @Before
    public void initialize() throws InterruptedException {
        MotechPage home = login();
        sleep(SLEEP_500);
        commcarePage = home.goToCommcare();
        sleep(SLEEP_500);
    }

    @After
    public void cleanUp() throws InterruptedException {
        logout();
    }
    //MOTECH 2181
    @Test
    public void shouldCheckIfSaveButtonIsDisabledWithoutConfiguration() throws InterruptedException {
        assertEquals(true,commcarePage.checkIfAddConfigurationButtonIsVisible());
        commcarePage.createNewConfiguration();
        assertEquals(false, commcarePage.checkIfSaveButtonIsDisabled());
    }

}