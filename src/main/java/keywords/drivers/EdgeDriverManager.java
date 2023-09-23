package keywords.drivers;


import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;


public class EdgeDriverManager extends DriverManager {
    @Override
    protected void createDriver() {
        //  WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
//        options.addArguments("--headless");
        driver = new EdgeDriver(options);
    }
}
