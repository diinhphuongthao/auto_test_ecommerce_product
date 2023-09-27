package test;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import keywords.WebUI;
import keywords.helper.LogHelper;
import org.slf4j.Logger;

    @CucumberOptions(
            features = {"src/main/resources/features/test.feature"},
            glue = {"step", "step.Hooks"},
            plugin = {"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"}
    )
    public class TestNGRunner extends AbstractTestNGCucumberTests {
        protected static Logger logger = LogHelper.getLogger();
        protected static WebUI webUI;
        public TestNGRunner() {
            logger.info("TestNGRunner");
            webUI = new WebUI();
        }
}
