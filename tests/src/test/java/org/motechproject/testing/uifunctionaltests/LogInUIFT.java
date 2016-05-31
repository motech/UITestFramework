package org.motechproject.testing.uifunctionaltests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.motechproject.uitest.TestBase;
import org.motechproject.uitest.page.LoginPage;
import org.motechproject.uitest.page.MotechPage;

import static org.junit.Assert.assertEquals;

public class LogInUIFT extends TestBase {

    private LoginPage loginPage;
    private MotechPage motechPage;

    @Before
    public void initialize() throws InterruptedException {
        motechPage = login();
    }

    //MOTECH 2229
    @Test
    public void shouldCheckIfRetryLoginPageWork() throws InterruptedException {
        loginPage = motechPage.logOut();
        loginPage.loginWithWrongPassword("Motech", "WrongPassword");
        assertEquals(true, loginPage.checkIfStatementForWrongPasswordAppears());
        MotechPage home = login();
        assertEquals(true, home.checkIfDataServicesModuleIsVisible());
    }

    @After
    public void cleanUp() throws InterruptedException {
        logout();
    }
}