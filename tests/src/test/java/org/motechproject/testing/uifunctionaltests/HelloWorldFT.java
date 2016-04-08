package org.motechproject.testing.uifunctionaltests;

import org.junit.Test;
import org.motechproject.uitest.TestBase;

public class HelloWorldFT extends TestBase {

    @Test
    public void testHelloWorld() {
        getLogger().debug("Hello world!");
        login();
    }
}
