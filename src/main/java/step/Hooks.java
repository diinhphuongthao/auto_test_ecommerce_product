package step;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import test.TestNGRunner;

public class Hooks extends TestNGRunner {


    @BeforeAll
    public static void beforeAll() {
        logger.info("Before All");
        webUI.openBrowser("Edge");
    }

    @AfterAll
    public static void afterAll() {
        webUI.closeBrowser();
        logger.info("After All");
    }

}